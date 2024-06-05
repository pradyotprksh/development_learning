package com.pradyotprakash.xfullstack.data.tweet

import com.mongodb.kotlin.client.coroutine.ChangeStreamFlow
import com.pradyotprakash.xfullstack.data.tweet.data.PollChoices
import com.pradyotprakash.xfullstack.data.tweet.data.Tweet

interface TweetDataSource {
    suspend fun getAllTweets(
        page: Int,
        limit: Int,
    ): List<Tweet>

    suspend fun watchTweets(): ChangeStreamFlow<Tweet>

    suspend fun findTweetById(tweetId: String): Tweet?

    suspend fun insertNewTweets(tweets: List<Tweet>): Boolean

    suspend fun incrementVotesOnPoll(
        tweetId: String,
        choices: List<PollChoices>
    ): Boolean

    suspend fun isLikedByCurrentUser(tweetId: String, userId: String): Boolean

    suspend fun totalNumberOfLikes(tweetId: String): Int
}