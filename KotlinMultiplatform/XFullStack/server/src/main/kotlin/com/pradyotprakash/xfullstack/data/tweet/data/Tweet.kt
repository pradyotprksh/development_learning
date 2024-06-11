package com.pradyotprakash.xfullstack.data.tweet.data

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.COMMENT_COUNT
import utils.Constants.DbKeys.CREATED_BY
import utils.Constants.DbKeys.IS_A_COMMENT_TWEET
import utils.Constants.DbKeys.IS_A_LIKED_TWEET
import utils.Constants.DbKeys.IS_A_POLL
import utils.Constants.DbKeys.IS_A_QUOTE_TWEET
import utils.Constants.DbKeys.IS_A_REPOST_TWEET
import utils.Constants.DbKeys.PARENT_TWEET_ID
import utils.Constants.DbKeys.POLL_CHOICES
import utils.Constants.DbKeys.POLL_LENGTH
import utils.Constants.DbKeys.SCHEDULED_ON_TWEET
import utils.Constants.DbKeys.TWEETED_ON

data class Tweet(
    // Main tweet details
    @BsonId val id: ObjectId = ObjectId(),
    val tweet: String,
    @BsonProperty(CREATED_BY) val createdBy: ObjectId,
    @BsonProperty(TWEETED_ON) val tweetedOn: Long,
    val media: List<String>,
    val gif: List<String>,
    @BsonProperty(COMMENT_COUNT) val commentCount: Int,

    // Poll tweet details
    @BsonProperty(IS_A_POLL) val isAPoll: Boolean,
    @BsonProperty(POLL_CHOICES) val pollChoices: List<PollChoices>,
    @BsonProperty(POLL_LENGTH) val pollLength: Long?,

    // Scheduled tweet details
    @BsonProperty(SCHEDULED_ON_TWEET) val scheduledOnTweet: Long,

    // Location tweet details
    val location: String,

    // Comment tweet details
    @BsonProperty(IS_A_COMMENT_TWEET) val isACommentTweet: Boolean,

    // Retweet details
    @BsonProperty(IS_A_QUOTE_TWEET) val isQuoteTweet: Boolean,
    @BsonProperty(IS_A_REPOST_TWEET) val isRepostTweet: Boolean,

    // Liked comment
    @BsonProperty(IS_A_LIKED_TWEET) val isLikedTweet: Boolean,

    // For comment, liked and retweet parent id will be required
    @BsonProperty(PARENT_TWEET_ID) val parentTweetId: ObjectId?,

    // Tweet emotions, if tweet has a value
    val emotions: List<String>,
)
