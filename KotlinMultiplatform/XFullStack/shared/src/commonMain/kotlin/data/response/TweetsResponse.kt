package data.response

import kotlinx.serialization.Serializable

@Serializable
data class TweetsResponse(
    val id: String,
    val tweet: String,
    val createdBy: UserInfoResponse,
    val tweetedOn: Long,
    val media: List<String>,
    val gif: List<String>,
    val commentCount: Int,
    val retweetCount: Int,
    val likesCount: Int,
    val views: Int,
    val isAPoll: Boolean,
    val pollChoices: List<PollChoicesResponse>,
    val isPollingAllowed: Boolean,
    val scheduledOnTweet: Long,
    val location: String,
    val isACommentTweet: Boolean,
    val isQuoteTweet: Boolean,
    val isRepostTweet: Boolean,
    val isLikedTweet: Boolean,
    val parentTweetId: String?,
)