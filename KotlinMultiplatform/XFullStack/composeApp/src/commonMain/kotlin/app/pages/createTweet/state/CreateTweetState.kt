package app.pages.createTweet.state

import utils.Constants.ConstValues.MAX_TWEET_CREATION_LIMIT
import utils.Localization

data class CreateTweetState(
    val tweets: List<TweetDetails> = List(
        size = MAX_TWEET_CREATION_LIMIT,
        init = { index ->
            if (index == 0) TweetDetails(
                isVisible = true,
                index = index,
                label = Localization.WHATS_HAPPENING,
            ) else TweetDetails(
                index = index,
                label = Localization.ADD_ANOTHER_TWEET,
            )
        },
    ),
    val multipleTweets: Boolean = false,
    val currentSelectedTweetLength: Int = 0,
    val media: List<String> = emptyList(),
    val gifs: List<String> = emptyList(),
    val pollChoices: List<String> = emptyList(),
    val profileImage: String? = null,
    val enableAddNewTweetButton: Boolean = false,
    val location: String = "",
    val currentPostLength: Int = 0,
)
