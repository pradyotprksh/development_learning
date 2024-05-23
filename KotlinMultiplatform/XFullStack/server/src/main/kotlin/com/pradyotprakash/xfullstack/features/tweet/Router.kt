package com.pradyotprakash.xfullstack.features.tweet

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.tweet.controllers.TweetController
import com.pradyotprakash.xfullstack.features.tweet.resource.TweetResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.tweet(
    tweetController: TweetController,
    tweetDataSource: TweetDataSource,
    userDataSource: UserDataSource,
) {
    authenticate {
        get<TweetResource> { }

        post<TweetResource> { }
    }
}