package data.request

data class TweetRequest(
    val tweet: String?,
    val createdBy: String,
    val media: List<String> = emptyList(),
    val gif: List<String> = emptyList(),

    // Poll tweet details
    val isAPoll: Boolean = false,
    val pollChoices: List<String> = emptyList(),
    val pollLength: Long? = null,

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
        get() = isRepostTweet && isLikedTweet
}
