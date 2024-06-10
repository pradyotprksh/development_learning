package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class TweetRequest(
    val tweet: String?,
    val media: List<String> = emptyList(),
    val gif: List<String> = emptyList(),

    // Poll tweet details
    val isAPoll: Boolean = false,
    val pollChoices: List<String> = emptyList(),
    val pollHour: Long? = null,
    val pollMinute: Long? = null,
    val pollSeconds: Long? = null,

    // Scheduled tweet details
    val isScheduledTweet: Boolean = false,
    val scheduledOnTweet: Long? = null,

    // Location tweet details
    val location: String? = null,

    // Comment tweet details
    val isACommentTweet: Boolean = false,
    val parentTweetId: String? = null,

    // Retweet details
    val isQuoteTweet: Boolean = false,
    val isRepostTweet: Boolean = false,

    // Liked comment
    val isLikedTweet: Boolean = false,
) {
    val tweetIsOptional: Boolean
        get() = isRepostTweet || isLikedTweet || media.isNotEmpty() || gif.isNotEmpty()

    val parentTweetIdIsRequired: Boolean
        get() = isACommentTweet || isLikedTweet || isRepostTweet || isQuoteTweet
}
