package com.pradyotprakash.xfullstack.features.bookmark.controllers.bookmarkUpdate

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.bookmark.resource.BookmarkResource
import io.ktor.server.application.ApplicationCall

class BookmarkUpdateControllerImplementation : BookmarkUpdateController {
    override suspend fun updateFollower(
        call: ApplicationCall,
        resource: BookmarkResource.Update,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
    ) {
    }
}