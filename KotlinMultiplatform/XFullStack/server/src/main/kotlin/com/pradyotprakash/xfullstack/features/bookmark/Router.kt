package com.pradyotprakash.xfullstack.features.bookmark

import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.bookmark.controllers.BookmarkController
import com.pradyotprakash.xfullstack.features.bookmark.resource.BookmarkResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.bookmark(
    bookmarkController: BookmarkController,
    userDataSource: UserDataSource,
    tweetDataSource: TweetDataSource,
    bookmarkDataSource: BookmarkDataSource,
) {
    authenticate {
        post<BookmarkResource.Update> {
            bookmarkController.updateFollower(
                this.context,
                it,
                userDataSource,
                tweetDataSource,
                bookmarkDataSource,
            )
        }
    }
}