package com.pradyotprakash.xfullstack.data.follow

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import org.bson.types.ObjectId
import utils.Constants.Database.Collections.FOLLOW
import utils.Constants.DbKeys.FOLLOWER_ID
import utils.Constants.DbKeys.FOLLOWING_ID

class MongoFollowDataSource(
    db: MongoDatabase,
) : FollowDataSource {
    private val followCollection = db.getCollection<Follow>(FOLLOW)

    override suspend fun addFollower(follow: Follow): Boolean {
        return followCollection.insertOne(follow).wasAcknowledged()
    }

    override suspend fun removeFollower(followerId: String, followingId: String): Boolean {
        return followCollection.findOneAndDelete(
            Filters.and(
                Filters.eq(FOLLOWER_ID, ObjectId(followerId)),
                Filters.eq(FOLLOWING_ID, ObjectId(followingId)),
            ),
        ) != null
    }
}