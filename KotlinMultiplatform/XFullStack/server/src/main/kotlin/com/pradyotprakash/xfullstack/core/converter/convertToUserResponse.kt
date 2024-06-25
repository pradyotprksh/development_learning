package com.pradyotprakash.xfullstack.core.converter

import com.pradyotprakash.xfullstack.core.data.parseToUserInfoResponse
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.User
import core.models.response.UserInfoResponse
import org.bson.types.ObjectId

suspend fun convertToUserResponse(
    followDataSource: FollowDataSource,
    chatDataSource: ChatDataSource,
    user: User,
    currentUserId: String,
): UserInfoResponse {
    val followersCount = followDataSource.getFollowerCount(user.id.toHexString())
    val followingCount = followDataSource.getFollowingCount(user.id.toHexString())
    val isFollowingCurrentUser =
        followDataSource.isFollowingCurrentUser(currentUserId, user.id.toHexString())
    val isFollowedByCurrentUser =
        followDataSource.isFollowedByCurrentUser(currentUserId, user.id.toHexString())
    val isSameUser = currentUserId == user.id.toHexString()
    val chatId = if (isSameUser) null else chatDataSource.chatDetailsByUsers(
        listOf(
            user.id,
            ObjectId(currentUserId),
        )
    )?.id?.toHexString()

    return user.parseToUserInfoResponse(
        followers = followersCount,
        following = followingCount,
        isFollowingCurrentUser = isFollowingCurrentUser,
        isFollowedByCurrentUser = isFollowedByCurrentUser,
        isSameUser = isSameUser,
        chatId = chatId,
    )
}