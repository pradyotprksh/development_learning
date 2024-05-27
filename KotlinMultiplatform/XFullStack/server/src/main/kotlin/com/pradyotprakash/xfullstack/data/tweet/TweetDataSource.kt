package com.pradyotprakash.xfullstack.data.tweet

import com.pradyotprakash.xfullstack.data.tweet.data.PollChoices
import com.pradyotprakash.xfullstack.data.tweet.data.Tweet

interface TweetDataSource {
    suspend fun getAllTweets(
        page: Int,
        limit: Int,
    ): List<Tweet>

    suspend fun findTweetById(tweetId: String): Tweet?

    suspend fun insertNewTweets(tweets: List<Tweet>): Boolean

    suspend fun incrementVotesOnPoll(
        tweetId: String,
        choices: List<PollChoices>
    ): Boolean
}