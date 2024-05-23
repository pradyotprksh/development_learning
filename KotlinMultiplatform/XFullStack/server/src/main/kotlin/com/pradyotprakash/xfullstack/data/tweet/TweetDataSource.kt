package com.pradyotprakash.xfullstack.data.tweet

interface TweetDataSource {
    suspend fun insertNewTweets(tweets: List<Tweet>): Boolean
}