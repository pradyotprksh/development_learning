package com.pradyotprakash.xfullstack.features.chat

import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.message.MessageDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.chat.controllers.ChatController
import com.pradyotprakash.xfullstack.features.chat.resource.ChatResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.chat(
    chatController: ChatController,
    chatDataSource: ChatDataSource,
    messageDataSource: MessageDataSource,
    userDataSource: UserDataSource,
    tweetDataSource: TweetDataSource,
) {
    authenticate {
        post<ChatResource.SendMessage> {
            chatController.sendMessage(
                this.context,
                userDataSource,
                tweetDataSource,
                chatDataSource,
                messageDataSource,
            )
        }
    }
}