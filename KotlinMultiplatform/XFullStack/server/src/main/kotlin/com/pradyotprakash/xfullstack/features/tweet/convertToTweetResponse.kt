package com.pradyotprakash.xfullstack.features.tweet

import com.pradyotprakash.xfullstack.core.data.parseToUserInfoResponse
import com.pradyotprakash.xfullstack.data.bookmark.BookmarkDataSource
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.tweet.data.Tweet
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import core.models.response.PollChoicesResponse
import core.models.response.TweetResponse
import org.bson.types.ObjectId
import utils.Localization
import utils.UtilsMethod

suspend fun convertToTweetResponse(
    userDataSource: UserDataSource,
    tweetDataSource: TweetDataSource,
    viewDataSource: ViewDataSource,
    followDataSource: FollowDataSource,
    bookmarkDataSource: BookmarkDataSource,
    chatDataSource: ChatDataSource,
    tweet: Tweet,
    currentUserId: String,
): TweetResponse {
    val tweetIdHexStr = tweet.id.toHexString()

    val createdByUserDetails = userDataSource.getUserByUserId(tweet.createdBy.toHexString())

    val allowCurrentUserVote =
        tweet.createdBy.toHexString() != currentUserId && tweet.pollChoices.find { pollChoice ->
            pollChoice.voterDetails.map { voter -> voter.votedBy }.contains(ObjectId(currentUserId))
        } == null
    val isPollingAllowed = UtilsMethod.Dates.isFutureTimeStamp(tweet.pollLength ?: 0)
    val isAPoll = tweet.isAPoll
    val pollingEndTime = tweet.pollLength?.let {
        if (isPollingAllowed) {
            UtilsMethod.Dates.timeLeft(it)
        } else if (isAPoll) {
            Localization.FINAL_RESULTS
        } else {
            ""
        }
    } ?: ""

    var parentTweetDetailsNotFound = false
    val parentTweetDetails = tweet.parentTweetId?.let {
        val result = tweetDataSource.findTweetById(it.toHexString())
        if (result == null) {
            parentTweetDetailsNotFound = true
        }
        result
    }

    val isLikedByCurrentUser = tweetDataSource.isLikedByCurrentUser(tweetIdHexStr, currentUserId)

    val likesCount = tweetDataSource.totalNumberOfLikes(tweetIdHexStr)
    val viewsCount = viewDataSource.getViewsCount(tweetIdHexStr)
    val repostsCount = tweetDataSource.totalNumberOfReposts(tweetIdHexStr)
    val quoteCount = tweetDataSource.totalNumberOfQuotes(tweetIdHexStr)
    val replyCount = tweetDataSource.totalNumberOfReplies(tweetIdHexStr)
    val isFollowingCurrentUser =
        followDataSource.isFollowingCurrentUser(currentUserId, tweet.createdBy.toHexString())
    val isFollowedByCurrentUser =
        followDataSource.isFollowedByCurrentUser(currentUserId, tweet.createdBy.toHexString())
    val followersCount = followDataSource.getFollowerCount(tweet.createdBy.toHexString())
    val followingCount = followDataSource.getFollowingCount(tweet.createdBy.toHexString())
    val isBookmarkedByCurrentUser =
        bookmarkDataSource.isBookmarkedCurrentUser(currentUserId, tweetIdHexStr)
    val bookmarkCount = bookmarkDataSource.getBookmarkCount(tweetIdHexStr)
    val isSameUser = currentUserId == tweet.createdBy.toHexString()
    val chatId = if (isSameUser) null else chatDataSource.chatDetailsByUsers(
        listOf(
            tweet.createdBy,
            ObjectId(currentUserId),
        )
    )?.id?.toHexString()

    return TweetResponse(
        id = tweetIdHexStr,
        tweet = tweet.tweet,
        createdBy = createdByUserDetails?.parseToUserInfoResponse(
            followers = followersCount,
            following = followingCount,
            isFollowingCurrentUser = isFollowingCurrentUser,
            isFollowedByCurrentUser = isFollowedByCurrentUser,
            isSameUser = isSameUser,
            chatId = chatId,
        ),
        tweetedOnTimestamp = tweet.tweetedOn,
        tweetedOn = UtilsMethod.Dates.convertTimestampToTimeAgo(tweet.tweetedOn),
        media = tweet.media,
        gif = tweet.gif,
        commentCount = replyCount,
        likesCount = likesCount,
        repostsCount = repostsCount,
        quotesCount = quoteCount,
        bookmarksCount = bookmarkCount,
        views = viewsCount,
        isAPoll = isAPoll,
        pollChoices = tweet.pollChoices.map { pollChoice ->
            PollChoicesResponse(
                id = pollChoice.id.toHexString(),
                choice = pollChoice.choice,
                voteCount = pollChoice.voteCount,
            )
        },
        pollingEndTime = pollingEndTime,
        isPollingAllowed = allowCurrentUserVote && isPollingAllowed,
        scheduledOnTweet = tweet.scheduledOnTweet,
        location = tweet.location,
        isACommentTweet = tweet.isACommentTweet,
        isQuoteTweet = tweet.isQuoteTweet,
        isRepostTweet = tweet.isRepostTweet,
        isLikedTweet = tweet.isLikedTweet,
        isLikedByCurrentUser = isLikedByCurrentUser,
        isBookmarkedByCurrentUser = isBookmarkedByCurrentUser,
        parentTweetId = tweet.parentTweetId?.toHexString(),
        parentTweetDetailsNotFound = parentTweetDetailsNotFound,
        parentTweetDetails = parentTweetDetails?.let {
            convertToTweetResponse(
                userDataSource,
                tweetDataSource,
                viewDataSource,
                followDataSource,
                bookmarkDataSource,
                chatDataSource,
                it,
                currentUserId
            )
        },
    )
}