package com.pradyotprakash.xfullstack.data.tweet

import com.mongodb.kotlin.client.coroutine.MongoDatabase
import utils.Constants.Database.Collections.TWEET

class MongoTweetDataSource(
    db: MongoDatabase,
) : TweetDataSource {
    private val tweetCollection = db.getCollection<Tweet>(TWEET)

    override suspend fun insertNewTweets(tweets: List<Tweet>): Boolean {
        return tweetCollection.insertMany(tweets).wasAcknowledged()
    }
}