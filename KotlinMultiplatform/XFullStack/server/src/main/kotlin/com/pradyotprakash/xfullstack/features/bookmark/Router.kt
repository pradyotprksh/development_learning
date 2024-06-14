package com.pradyotprakash.xfullstack.features.bookmark

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.bookmark.controllers.BookmarkController
import io.ktor.server.routing.Routing

fun Routing.bookmark(
    bookmarkController: BookmarkController,
    userDataSource: UserDataSource,
    tweetDataSource: TweetDataSource,
) {
}