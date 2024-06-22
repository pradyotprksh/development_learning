package app.pages.directMessage.state

import core.models.response.UserInfoResponse

data class DirectMessageState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val userInfo: UserInfoResponse? = null,
)
