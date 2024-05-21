package com.pradyotprakash.xfullstack.features.tweets

import com.pradyotprakash.xfullstack.features.tweets.controllers.TweetsController
import com.pradyotprakash.xfullstack.features.tweets.resource.TweetsResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.tweets(
    tweetsController: TweetsController,
) {
    authenticate {
        get<TweetsResource> { }

        post<TweetsResource> { }
    }
}