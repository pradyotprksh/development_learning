package com.pradyotprakash.xfullstack.features.websockets

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import io.ktor.server.routing.Routing

fun Routing.websockets(
    tweetDataSource: TweetDataSource,
    userDataSource: UserDataSource,
) {
}