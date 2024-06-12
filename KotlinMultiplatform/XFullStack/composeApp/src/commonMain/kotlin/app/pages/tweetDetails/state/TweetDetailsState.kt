package app.pages.tweetDetails.state

import core.models.response.TweetResponse

data class TweetDetailsState(
    val snackBarMessage: String? = null,
    val tweet: TweetResponse? = null,
    val replies: List<TweetResponse> = emptyList(),
    val replyTweet: String = "",
    val isReplyTweetFocused: Boolean = false,
)
