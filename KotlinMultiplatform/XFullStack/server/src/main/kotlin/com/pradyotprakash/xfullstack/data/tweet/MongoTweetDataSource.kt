package com.pradyotprakash.xfullstack.data.tweet

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.pradyotprakash.xfullstack.data.tweet.data.Tweet
import kotlinx.coroutines.flow.firstOrNull
import org.bson.types.ObjectId
import utils.Constants.Database.Collections.TWEET
import utils.Constants.DbKeys.ID

class MongoTweetDataSource(
    db: MongoDatabase,
) : TweetDataSource {
    private val tweetCollection = db.getCollection<Tweet>(TWEET)
    override suspend fun findTweetById(tweetId: String): Tweet? {
        return tweetCollection.find(
            Filters.eq(ID, ObjectId(tweetId))
        ).limit(1).firstOrNull()
    }

    override suspend fun insertNewTweets(tweets: List<Tweet>): Boolean {
        return tweetCollection.insertMany(tweets).wasAcknowledged()
    }
}