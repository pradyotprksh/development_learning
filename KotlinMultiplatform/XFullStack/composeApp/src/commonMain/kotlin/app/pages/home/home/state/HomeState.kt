package app.pages.home.home.state

import core.models.realm.TweetDB
import utils.Localization

data class HomeState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val profileImage: String? = null,
    val name: String = "",
    val username: String = "",
    val following: Int = 0,
    val followers: Int = 0,
    val tweets: List<TweetDB> = emptyList(),
    val tabsDetails: List<String> = listOf(
        Localization.FOR_YOU,
        Localization.FOLLOWING,
    ),
)