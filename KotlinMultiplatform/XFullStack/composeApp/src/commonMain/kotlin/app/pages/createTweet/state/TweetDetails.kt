package app.pages.createTweet.state

data class TweetDetails(
    val tweet: String = "",
    val isVisible: Boolean = false,
    val index: Int = -1,
)
