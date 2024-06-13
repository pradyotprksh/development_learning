package com.pradyotprakash.xfullstack.data.follow

interface FollowDataSource {
    suspend fun addFollower(follow: Follow): Boolean

    suspend fun removeFollower(followerId: String, followingId: String): Boolean
}