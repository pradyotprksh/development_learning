package app.pages.home.home.state

import core.models.response.TweetResponse
import utils.Localization

data class HomeState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val profileImage: String? = null,
    val name: String = "",
    val username: String = "",
    val following: Int = 0,
    val followers: Int = 0,
    val forYouTweets: List<TweetResponse> = emptyList(),
    val isNewTweetFound: Boolean = true,
    val forYouTweetPage: Int = 1,
    val followingTweetPage: Int = 1,
    val firstLoadDone: Boolean = false,
    val canPaginate: Boolean = false,
    val tabsDetails: List<String> = listOf(
        Localization.FOR_YOU,
        Localization.FOLLOWING,
    ),
)