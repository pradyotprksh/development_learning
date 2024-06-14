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
    val currentFocusedTweetIndex: Int = -1,
    val enableAddNewTweetButton: Boolean = false,
    val showAddNewTweetButton: Boolean = true,
    val currentPostLength: Int = 0,
    val profileImage: String? = null,
)
