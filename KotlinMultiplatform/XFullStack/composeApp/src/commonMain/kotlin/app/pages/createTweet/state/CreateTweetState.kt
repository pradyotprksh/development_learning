package app.pages.createTweet.state

data class CreateTweetState(
    val tweets: List<String> = emptyList(),
    val media: List<String> = emptyList(),
    val gifs: List<String> = emptyList(),
    val pollChoices: List<String> = emptyList(),
    val location: String = "",
    val currentPostLength: Int = 0,
)
