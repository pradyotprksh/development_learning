package com.pradyotprakash.xfullstack.data.tweet

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Tweet(
    // Main tweet details
    @BsonId val id: ObjectId = ObjectId(),
    val tweet: String,
    val createdBy: String,
    val tweetedOn: Long,
    val media: List<String>,
    val gif: List<String>,
    val commentCount: Int,
    val retweetCount: Int,
    val likeCount: Int,
    val views: Int,

    // Poll tweet details
    val isAPoll: Boolean,
    val pollChoices: List<String>,
    val pollLength: Long,

    // Scheduled tweet details
    val scheduledOnTweet: Long,
    val isScheduledTweet: Boolean,

    // Location tweet details
    val location: String,

    // Comment tweet details
    val isACommentTweet: Boolean,
    val parentTweetId: ObjectId?,

    // Retweet details
    val isQuoteTweet: Boolean,
    val isRepostTweet: Boolean,

    // Liked comment
    val isLikedTweet: Boolean,
)
