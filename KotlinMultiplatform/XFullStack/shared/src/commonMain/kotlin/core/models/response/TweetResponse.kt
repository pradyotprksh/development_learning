package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class TweetResponse(
    val id: String,
    val tweet: String,
    val createdBy: UserInfoResponse?,
    val tweetedOnTimestamp: Long,
    val tweetedOn: String,
    val media: List<String>,
    val gif: List<String>,
    val commentCount: Int,
    val repostsCount: Int,
    val quotesCount: Int,
    val bookmarksCount: Int,
    val likesCount: Int,
    val views: Int,
    val isAPoll: Boolean,
    val pollChoices: List<PollChoicesResponse>,
    val isPollingAllowed: Boolean,
    val pollingEndTime: String,
    val scheduledOnTweet: Long,
    val location: String,
    val isACommentTweet: Boolean,
    val isQuoteTweet: Boolean,
    val isRepostTweet: Boolean,
    val isLikedTweet: Boolean,
    val parentTweetId: String?,
    val parentTweetDetails: TweetResponse?,
    val parentTweetDetailsNotFound: Boolean,
    val isLikedByCurrentUser: Boolean,
) {
    val aInnerTweet: Boolean
        get() = isRepostTweet || isLikedTweet

    val parentTweetNotPresent: Boolean
        get() = parentTweetDetailsNotFound && parentTweetDetails == null

    val retweetCount: Int
        get() = repostsCount + quotesCount
}