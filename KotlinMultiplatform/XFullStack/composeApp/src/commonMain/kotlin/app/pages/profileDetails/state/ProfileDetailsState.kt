package app.pages.profileDetails.state

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
)
