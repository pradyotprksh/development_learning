package com.pradyotprakash.xfullstack.data.tweet

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.pradyotprakash.xfullstack.data.tweet.data.PollChoices
import com.pradyotprakash.xfullstack.data.tweet.data.Tweet
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import utils.Constants.Database.Collections.TWEET
import utils.Constants.DbKeys.ID
import utils.Constants.DbKeys.POLL_CHOICES
import utils.Constants.DbKeys.TWEETED_ON

class MongoTweetDataSource(
    db: MongoDatabase,
) : TweetDataSource {
    private val tweetCollection = db.getCollection<Tweet>(TWEET)
    override suspend fun getAllTweets(
        page: Int,
        limit: Int,
    ): List<Tweet> {
        val skip = (page - 1) * limit
        return tweetCollection.find().sort(Sorts.descending(TWEETED_ON)).skip(skip).limit(limit)
            .toList()
    }

    override suspend fun watchTweets() = tweetCollection.watch()

    override suspend fun findTweetById(tweetId: String): Tweet? {
        return tweetCollection.find(
            Filters.eq(ID, ObjectId(tweetId))
        ).limit(1).firstOrNull()
    }

    override suspend fun insertNewTweets(tweets: List<Tweet>): Boolean {
        return tweetCollection.insertMany(tweets).wasAcknowledged()
    }

    override suspend fun incrementVotesOnPoll(
        tweetId: String,
        choices: List<PollChoices>
    ): Boolean {
        return tweetCollection.updateOne(
            filter = Filters.eq(ID, ObjectId(tweetId)),
            update = Updates.set(POLL_CHOICES, choices)
        ).wasAcknowledged()
    }
}