package com.pradyotprakash.xfullstack.features.chat.controllers.fetchMessages

import com.pradyotprakash.xfullstack.core.converter.convertToTweetResponse
import com.pradyotprakash.xfullstack.core.converter.convertToUserResponse
import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.message.Message
import com.pradyotprakash.xfullstack.data.message.MessageDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.features.chat.resource.ChatResource
import core.exception.InvalidMessage
import core.exception.UserDetailsNotFound
import core.models.response.ChatResponse
import core.models.response.FetchMessageResponse
import core.models.response.MessageResponse
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class FetchMessagesControllerImplementation : FetchMessagesController {
    override suspend fun getMessages(
        call: ApplicationCall,
        resource: ChatResource.GetMessagesResource,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        chatDataSource: ChatDataSource,
        messageDataSource: MessageDataSource,
        viewDataSource: ViewDataSource,
        followDataSource: FollowDataSource,
        bookmarkDataSource: BookmarkDataSource,
    ) {
        val principal = call.principal<JWTPrincipal>()
        val currentUserId =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(currentUserId) ?: throw UserDetailsNotFound()

        val chatId = resource.chatId

        val chatDetails = chatDataSource.chatDetails(chatId) ?: throw InvalidMessage(
            message = Localization.NOT_ABLE_TO_FIND_CHAT,
        )

        val messagesResponse = messageDataSource.getMessages(chatId).map { message ->
            parseMessageToMessageResponse(
                message,
                chatId,
                currentUserId,
                userDataSource,
                tweetDataSource,
                chatDataSource,
                messageDataSource,
                viewDataSource,
                followDataSource,
                bookmarkDataSource,
            )
        }

        val chatResponse = ChatResponse(
            chatId = chatDetails.id.toHexString(),
            users = chatDetails.users.mapNotNull {
                userDataSource.getUserByUserId(it.toHexString())?.let { user ->
                    convertToUserResponse(
                        followDataSource,
                        chatDataSource,
                        user,
                        currentUserId,
                    )
                }
            },
            createdBy = chatDetails.createdBy.toHexString(),
            createdOn = chatDetails.createdOn,
            isGroup = chatDetails.isGroup,
            isDirect = chatDetails.isDirect,
            lastMessageDetails = messagesResponse.firstOrNull(),
        )

        call.respond(
            HttpStatusCode.OK, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.DETAILS_FOUND,
                data = FetchMessageResponse(
                    chat = chatResponse,
                    messages = messagesResponse,
                )
            )
        )
    }

    override suspend fun getChats(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        chatDataSource: ChatDataSource,
        messageDataSource: MessageDataSource,
        viewDataSource: ViewDataSource,
        followDataSource: FollowDataSource,
        bookmarkDataSource: BookmarkDataSource,
    ) {
        val principal = call.principal<JWTPrincipal>()
        val currentUserId =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(currentUserId) ?: throw UserDetailsNotFound()

        val chats = chatDataSource.getChats(currentUserId).map { chatDetails ->
            val chatId = chatDetails.id.toHexString()
            val lastMessage =
                messageDataSource.getLastMessage(chatId)?.let { lastMessage ->
                    parseMessageToMessageResponse(
                        lastMessage,
                        chatId,
                        currentUserId,
                        userDataSource,
                        tweetDataSource,
                        chatDataSource,
                        messageDataSource,
                        viewDataSource,
                        followDataSource,
                        bookmarkDataSource,
                    )
                }

            ChatResponse(
                chatId = chatDetails.id.toHexString(),
                users = chatDetails.users.mapNotNull {
                    userDataSource.getUserByUserId(it.toHexString())?.let { user ->
                        convertToUserResponse(
                            followDataSource,
                            chatDataSource,
                            user,
                            currentUserId,
                        )
                    }
                },
                createdBy = chatDetails.createdBy.toHexString(),
                createdOn = chatDetails.createdOn,
                isGroup = chatDetails.isGroup,
                isDirect = chatDetails.isDirect,
                lastMessageDetails = lastMessage,
            )
        }

        call.respond(
            HttpStatusCode.OK, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.DETAILS_FOUND,
                data = chats
            )
        )
    }

    private suspend fun parseMessageToMessageResponse(
        message: Message,
        chatId: String,
        currentUserId: String,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        chatDataSource: ChatDataSource,
        messageDataSource: MessageDataSource,
        viewDataSource: ViewDataSource,
        followDataSource: FollowDataSource,
        bookmarkDataSource: BookmarkDataSource,
    ): MessageResponse {
        val replyMessageResponse =
            message.replyMessageId?.toHexString()?.let { messageDataSource.messageDetails(it) }
                ?.let {
                    parseMessageToMessageResponse(
                        it,
                        chatId,
                        currentUserId,
                        userDataSource,
                        tweetDataSource,
                        chatDataSource,
                        messageDataSource,
                        viewDataSource,
                        followDataSource,
                        bookmarkDataSource,
                    )
                }
        val forwardMessageResponse =
            message.forwardMessageId?.toHexString()?.let { messageDataSource.messageDetails(it) }
                ?.let {
                    parseMessageToMessageResponse(
                        it,
                        currentUserId,
                        chatId,
                        userDataSource,
                        tweetDataSource,
                        chatDataSource,
                        messageDataSource,
                        viewDataSource,
                        followDataSource,
                        bookmarkDataSource,
                    )
                }
        val tweetResponse =
            message.tweetId?.toHexString()?.let { tweetDataSource.findTweetById(it) }
                ?.let { tweet ->
                    convertToTweetResponse(
                        userDataSource,
                        tweetDataSource,
                        viewDataSource,
                        followDataSource,
                        bookmarkDataSource,
                        chatDataSource,
                        tweet,
                        currentUserId
                    )
                }
        val messageById = message.messageBy.toHexString()
        val isSendByCurrentUser = messageById == currentUserId
        val messageByUserDetails =
            userDataSource.getUserByUserId(messageById)?.let { user ->
                convertToUserResponse(
                    followDataSource,
                    chatDataSource,
                    user,
                    currentUserId,
                )
            }

        return MessageResponse(
            id = message.id.toHexString(),
            chatId = chatId,
            message = message.message,
            messageOn = message.messageOn,
            messageTime = UtilsMethod.Dates.convertLongToReadableTime(message.messageOn),
            messageTimeAgo = UtilsMethod.Dates.convertTimestampToTimeAgo(message.messageOn),
            messageBy = messageByUserDetails,
            isSendByCurrentUser = isSendByCurrentUser,
            messageGroup = UtilsMethod.Dates.convertLongToReadableDate(message.messageOn),
            isRead = message.isRead,
            media = message.media,
            gif = message.gif,
            replyMessageDetails = replyMessageResponse,
            notificationMessage = message.notificationMessage,
            forwardMessageDetails = forwardMessageResponse,
            reaction = message.reaction,
            audio = message.audio,
            tweetDetails = tweetResponse,
        )
    }
}