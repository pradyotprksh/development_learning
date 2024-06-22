package com.pradyotprakash.xfullstack.features.chat.controllers.fetchMessages

import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.message.MessageDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.features.chat.resource.ChatResource
import io.ktor.server.application.ApplicationCall

interface FetchMessagesController {
    suspend fun getMessages(
        call: ApplicationCall,
        resource: ChatResource.GetMessages,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        chatDataSource: ChatDataSource,
        messageDataSource: MessageDataSource,
        viewDataSource: ViewDataSource,
        followDataSource: FollowDataSource,
        bookmarkDataSource: BookmarkDataSource,
    )

    suspend fun getChats(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        chatDataSource: ChatDataSource,
        messageDataSource: MessageDataSource,
        viewDataSource: ViewDataSource,
        followDataSource: FollowDataSource,
        bookmarkDataSource: BookmarkDataSource,
    )
}