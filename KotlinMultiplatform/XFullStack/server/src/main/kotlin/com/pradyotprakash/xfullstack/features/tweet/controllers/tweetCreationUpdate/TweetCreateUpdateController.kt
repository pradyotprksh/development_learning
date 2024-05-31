package com.pradyotprakash.xfullstack.features.tweet.controllers.tweetCreationUpdate

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.tweet.resource.TweetResource
import io.ktor.server.application.ApplicationCall

interface TweetCreateUpdateController {
    suspend fun createTweet(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
    )

    suspend fun voteOnTweet(
        call: ApplicationCall,
        resource: TweetResource.TweetVote,
        userDataSource: UserDataSource,
        tweetDataSource: TweetDataSource,
    )
}