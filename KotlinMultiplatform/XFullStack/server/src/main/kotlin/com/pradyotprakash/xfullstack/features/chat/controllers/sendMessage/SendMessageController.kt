package com.pradyotprakash.xfullstack.features.chat.controllers.sendMessage

import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.message.MessageDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import io.ktor.server.application.ApplicationCall

interface SendMessageController {
    suspend fun sendMessage(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        chatDataSource: ChatDataSource,
        messageDataSource: MessageDataSource,
    )
}