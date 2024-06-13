package app.pages.profileDetails.state

import core.models.response.UserInfoResponse

data class ProfileDetailsState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val userInfoResponse: UserInfoResponse? = null,
)
