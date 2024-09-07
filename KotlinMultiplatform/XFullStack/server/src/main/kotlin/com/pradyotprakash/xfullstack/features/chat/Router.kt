package com.pradyotprakash.xfullstack.features.chat

import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.message.MessageDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.features.chat.controllers.ChatController
import com.pradyotprakash.xfullstack.features.chat.resource.ChatResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.chat(
    chatController: ChatController,
    chatDataSource: ChatDataSource,
    messageDataSource: MessageDataSource,
    userDataSource: UserDataSource,
    tweetDataSource: TweetDataSource,
    viewDataSource: ViewDataSource,
    followDataSource: FollowDataSource,
    bookmarkDataSource: BookmarkDataSource,
) {
    authenticate {
        post<ChatResource.SendMessageResource> {
            chatController.sendMessage(
                this.context,
                userDataSource,
                tweetDataSource,
                chatDataSource,
                messageDataSource,
            )
        }

        get<ChatResource.GetMessagesResource> {
            chatController.getMessages(
                this.context,
                it,
                userDataSource,
                tweetDataSource,
                chatDataSource,
                messageDataSource,
                viewDataSource,
                followDataSource,
                bookmarkDataSource,
            )
        }

        get<ChatResource.GetChatsResource> {
            chatController.getChats(
                this.context,
                userDataSource,
                tweetDataSource,
                chatDataSource,
                messageDataSource,
                viewDataSource,
                followDataSource,
                bookmarkDataSource,
            )
        }
    }
}