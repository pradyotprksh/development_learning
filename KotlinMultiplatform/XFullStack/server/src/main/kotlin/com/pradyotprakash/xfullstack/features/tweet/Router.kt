package com.pradyotprakash.xfullstack.features.tweet

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.tweet.controllers.TweetUpdateController
import com.pradyotprakash.xfullstack.features.tweet.resource.TweetResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.tweet(
    tweetController: TweetUpdateController,
    tweetDataSource: TweetDataSource,
    userDataSource: UserDataSource,
) {
    authenticate {
        get<TweetResource> {
            tweetController.getAllTweets(
                this.context,
                userDataSource,
                tweetDataSource,
            )
        }

        post<TweetResource> {
            tweetController.createTweet(
                this.context,
                userDataSource,
                tweetDataSource,
            )
        }

        post<TweetResource.TweetVote> {
            tweetController.voteOnTweet(
                this.context,
                it,
                userDataSource,
                tweetDataSource,
            )
        }
    }
}