package com.pradyotprakash.xfullstack.data.follow

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.firstOrNull
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

    override suspend fun getFollowerCount(userId: String): Int {
        return followCollection.find(
            Filters.eq(FOLLOWING_ID, ObjectId(userId)),
        ).count()
    }

    override suspend fun getFollowingCount(userId: String): Int {
        return followCollection.find(
            Filters.eq(FOLLOWER_ID, ObjectId(userId)),
        ).count()
    }

    override suspend fun watchFollowUpdate() = followCollection.watch()

    override suspend fun isFollowingCurrentUser(currentUserId: String, createdBy: String): Boolean {
        return followCollection.find(
            Filters.and(
                Filters.eq(FOLLOWING_ID, ObjectId(createdBy)),
                Filters.eq(FOLLOWER_ID, ObjectId(currentUserId)),
            )
        ).firstOrNull() != null
    }

    override suspend fun isFollowedByCurrentUser(
        currentUserId: String,
        createdBy: String,
    ): Boolean {
        return followCollection.find(
            Filters.and(
                Filters.eq(FOLLOWING_ID, ObjectId(currentUserId)),
                Filters.eq(FOLLOWER_ID, ObjectId(createdBy)),
            )
        ).firstOrNull() != null
    }
}