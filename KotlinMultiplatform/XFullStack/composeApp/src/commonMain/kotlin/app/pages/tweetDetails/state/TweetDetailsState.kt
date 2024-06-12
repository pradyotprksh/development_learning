package app.pages.tweetDetails.state

import core.models.response.TweetResponse

data class TweetDetailsState(
    val showLoading: Boolean = true,
    val snackBarMessage: String? = null,
    val tweet: TweetResponse? = null,
)
