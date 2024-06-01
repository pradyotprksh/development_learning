package app.pages.createTweet.state

data class CreateTweetState(
    val tweets: List<TweetDetails> = List(
        size = 25,
        init = { index ->
            if (index == 0) TweetDetails(
                isVisible = true,
                index = index,
            ) else TweetDetails(index = index)
        },
    ),
    val currentSelectedTweet: TweetDetails = TweetDetails(),
    val media: List<String> = emptyList(),
    val gifs: List<String> = emptyList(),
    val pollChoices: List<String> = emptyList(),
    val profileImage: String? = null,
    val enableAddNewTweetButton: Boolean = false,
    val location: String = "",
    val currentPostLength: Int = 0,
)
