package app.pages.profileDetails.state

import core.models.response.TweetResponse
import core.models.response.UserInfoResponse
import utils.Localization

data class ProfileDetailsState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val userInfoResponse: UserInfoResponse? = null,
    val tabsDetails: List<String> = listOf(
        Localization.POSTS,
        Localization.REPLIES,
        Localization.HIGHLIGHTS,
        Localization.MEDIA,
    ),
    val posts: List<TweetResponse> = emptyList(),
    val replies: List<TweetResponse> = emptyList(),
    val highlights: List<TweetResponse> = emptyList(),
    val media: List<TweetResponse> = emptyList(),
    val likes: List<TweetResponse> = emptyList(),
)
