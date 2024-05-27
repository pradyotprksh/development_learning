package com.pradyotprakash.xfullstack.features.tweet.controllers.tweetFetch

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import io.ktor.server.application.ApplicationCall

interface TweetFetchController {
    suspend fun getAllTweets(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
    )
}