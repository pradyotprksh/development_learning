package com.pradyotprakash.xfullstack.features.chat.controllers.sendMessage

import com.pradyotprakash.xfullstack.data.chat.Chat
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.message.MessageDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import core.exception.DBWriteError
import core.exception.InvalidMessage
import core.exception.UserDetailsNotFound
import core.models.request.MessageRequest
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import org.bson.types.ObjectId
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.UtilsMethod

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
        val createdBy =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(createdBy) ?: throw UserDetailsNotFound()

        val users = mutableListOf<String>()
        if (messageRequest.messageTo.isNotBlank()) {
            users.add(messageRequest.messageTo)
        }
        if (messageRequest.users.isEmpty()) {
            users.addAll(messageRequest.users)
        }
        users.forEach { id ->
            userDataSource.getUserByUserId(id) ?: throw UserDetailsNotFound()
        }
        users.add(createdBy)

        var chatId = messageRequest.chatId

        if (chatId.isBlank()) {
            if (users.size < 2) {
                throw InvalidMessage(
                    message = Localization.NEED_TWO_USERS,
                )
            }

            val chatDetails = Chat(
                users = users.map { ObjectId(it) },
                createdOn = UtilsMethod.Dates.getCurrentTimeStamp(),
                createdBy = ObjectId(createdBy),
                isGroup = users.size > 2,
                isDirect = users.size == 2,
            )

            val wasAcknowledged = chatDataSource.addChat(chatDetails)

            if (!wasAcknowledged) {
                throw DBWriteError()
            }

            chatId = chatDetails.id.toHexString()
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
    }
}