package com.pradyotprakash.xfullstack.features.chat.controllers.sendMessage

import com.pradyotprakash.xfullstack.data.chat.Chat
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.message.Message
import com.pradyotprakash.xfullstack.data.message.MessageDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import core.exception.DBWriteError
import core.exception.InvalidMessage
import core.exception.InvalidTweet
import core.exception.UserDetailsNotFound
import core.models.request.MessageRequest
import core.models.response.SendMessageResponse
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import org.bson.types.ObjectId
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class SendMessageControllerImplementation : SendMessageController {
    override suspend fun sendMessage(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        chatDataSource: ChatDataSource,
        messageDataSource: MessageDataSource,
    ) {
        val messageRequest = call.receive<MessageRequest>()

        val principal = call.principal<JWTPrincipal>()
        val sentBy =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(sentBy) ?: throw UserDetailsNotFound()

        val chatId = messageRequest.chatId.ifBlank {
            val users = mutableSetOf<String>()
            if (messageRequest.messageTo.isNotBlank()) {
                users.add(messageRequest.messageTo)
            }
            if (messageRequest.users.isEmpty()) {
                users.addAll(messageRequest.users)
            }
            users.removeIf { it.isBlank() }
            users.forEach { id ->
                userDataSource.getUserByUserId(id) ?: throw UserDetailsNotFound()
            }
            users.add(sentBy)

            if (users.size < 2) {
                throw InvalidMessage(
                    message = Localization.NEED_TWO_USERS,
                )
            }

            val usersObjectId = users.map { ObjectId(it) }

            val chatDetailsByUsers = chatDataSource.chatDetailsByUsers(usersObjectId)
            if (chatDetailsByUsers != null) {
                chatDetailsByUsers.id.toHexString()
            } else {
                val chatDetails = Chat(
                    users = usersObjectId,
                    createdOn = UtilsMethod.Dates.getCurrentTimeStamp(),
                    createdBy = ObjectId(sentBy),
                    isGroup = users.size > 2,
                    isDirect = users.size == 2,
                )

                val wasAcknowledged = chatDataSource.addChat(chatDetails)

                if (!wasAcknowledged) {
                    throw DBWriteError()
                }

                chatDetails.id.toHexString()
            }
        }

        val chatDetails = chatDataSource.chatDetails(chatId) ?: throw InvalidMessage(
            message = Localization.NOT_ABLE_TO_FIND_CHAT,
        )

        if (!messageRequest.isMessageOptional) {
            if (messageRequest.message.isBlank()) {
                throw InvalidMessage(
                    message = Localization.MESSAGE_IS_REQUIRED,
                )
            }
        }

        if (chatDetails.isDirect) {
            userDataSource.getUserByUserId(messageRequest.messageTo) ?: throw UserDetailsNotFound()
        } else if (chatDetails.isGroup) {
            chatDataSource.chatDetails(messageRequest.messageTo) ?: throw InvalidMessage(
                message = Localization.NOT_ABLE_TO_FIND_CHAT,
            )
        }

        val replyMessageId = if (messageRequest.replyMessageId.isNotBlank()) {
            messageDataSource.messageDetails(messageRequest.replyMessageId) ?: throw InvalidMessage(
                message = Localization.MESSAGE_DETAILS_NOT_FOUND,
            )
            ObjectId(messageRequest.replyMessageId)
        } else {
            null
        }

        val forwardMessageId = if (messageRequest.forwardMessageId.isNotBlank()) {
            messageDataSource.messageDetails(messageRequest.forwardMessageId)
                ?: throw InvalidMessage(
                    message = Localization.MESSAGE_DETAILS_NOT_FOUND,
                )
            ObjectId(messageRequest.forwardMessageId)
        } else {
            null
        }

        val tweetId = if (messageRequest.tweetId.isNotBlank()) {
            tweetDataSource.findTweetById(messageRequest.tweetId) ?: throw InvalidTweet(
                message = Localization.PARENT_TWEET_NOT_FOUND,
            )
            ObjectId(messageRequest.tweetId)
        } else {
            null
        }

        val messageDetails = Message(
            chatId = ObjectId(chatId),
            message = messageRequest.message,
            messageBy = ObjectId(sentBy),
            messageTo = ObjectId(messageRequest.messageTo),
            messageOn = UtilsMethod.Dates.getCurrentTimeStamp(),
            isRead = false,
            media = messageRequest.media,
            gif = messageRequest.gif,
            replyMessageId = replyMessageId,
            notificationMessage = false,
            forwardMessageId = forwardMessageId,
            reaction = emptyList(),
            audio = "",
            tweetId = tweetId,
        )

        val wasAcknowledged = messageDataSource.addMessage(messageDetails)

        if (!wasAcknowledged) {
            throw DBWriteError()
        }

        call.respond(
            HttpStatusCode.Created, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.MESSAGE_SENT_SUCCESSFULLY,
                data = SendMessageResponse(
                    chatId = chatId,
                ),
            )
        )
    }
}