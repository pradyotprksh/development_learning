package com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreation

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import io.ktor.server.application.ApplicationCall

interface TweetCreationController {
    suspend fun createTweet(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
    )
}