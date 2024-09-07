package com.pradyotprakash.xfullstack.features.bookmark.controllers.bookmarkUpdate

import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.bookmark.resource.BookmarkResource
import io.ktor.server.application.ApplicationCall

interface BookmarkUpdateController {
    suspend fun updateFollower(
        call: ApplicationCall,
        resource: BookmarkResource.UpdateResource,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
        bookmarkDataSource: BookmarkDataSource,
    )
}