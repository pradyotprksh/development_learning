package com.pradyotprakash.xfullstack.data.tweet

import com.pradyotprakash.xfullstack.data.tweet.data.Tweet

interface TweetDataSource {
    suspend fun findTweetById(tweetId: String): Tweet?

    suspend fun insertNewTweets(tweets: List<Tweet>): Boolean
}