package com.pradyotprakash.xfullstack.data.follow

interface FollowDataSource {
    suspend fun addFollower(follow: Follow): Boolean

    suspend fun removeFollower(followerId: String, followingId: String): Boolean

    suspend fun getFollowerCount(userId: String): Int

    suspend fun getFollowingCount(userId: String): Int

    suspend fun isFollowingCurrentUser(currentUserId: String, createdBy: String): Boolean

    suspend fun isFollowedByCurrentUser(currentUserId: String, createdBy: String): Boolean
}