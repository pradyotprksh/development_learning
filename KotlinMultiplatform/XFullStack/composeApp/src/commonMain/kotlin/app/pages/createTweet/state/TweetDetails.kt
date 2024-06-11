package app.pages.createTweet.state

import core.models.response.TweetResponse

data class TweetDetails(
    val tweet: String = "",
    val isVisible: Boolean = false,
    val index: Int = -1,
    val label: String = "",
    val media: List<String> = emptyList(),
    val gifs: List<String> = emptyList(),
    val isAPoll: Boolean = false,
    val pollChoices: List<String> = emptyList(),
    val pollHour: Long? = null,
    val pollMinute: Long? = null,
    val pollSeconds: Long? = null,
    val isLocation: Boolean = false,
    val location: String = "",
    val isScheduledTweet: Boolean = false,
    val scheduledOnTweet: Long? = null,
    val isQuoteTweet: Boolean = false,
    val isRepostTweet: Boolean = false,
    val parentTweetId: String? = null,
    val parentTweetDetails: TweetResponse? = null,
) {
    fun shouldSelectThisTweet(): Boolean {
        return validTweet() && isPollValid() && isLocationValid() && isScheduledValid() && isQuoteValid()
    }

    private fun validTweet(): Boolean {
        return isVisible && tweet.isNotBlank()
    }

    private fun isPollValid(): Boolean {
        if (isAPoll) {
            return pollChoices.none { it.isBlank() } && ((pollHour ?: 0) > 0 || (pollMinute
                ?: 0) > 0 || (pollSeconds ?: 0) > 0)
        }
        return true
    }

    private fun isLocationValid(): Boolean {
        if (isLocation) {
            return location.isNotBlank()
        }
        return true
    }

    private fun isScheduledValid(): Boolean {
        if (isScheduledTweet) {
            return scheduledOnTweet != null
        }
        return true
    }

    private fun isQuoteValid(): Boolean {
        if (isQuoteTweet) {
            return parentTweetId != null
        }
        return true
    }
}
