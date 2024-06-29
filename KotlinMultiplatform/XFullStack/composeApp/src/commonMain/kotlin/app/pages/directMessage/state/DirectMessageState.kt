package app.pages.directMessage.state

import core.models.response.UserInfoResponse

data class DirectMessageState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val chatId: String? = null,
    val usersInfo: List<UserInfoResponse> = emptyList(),
    val messages: List<MessageDetails> = emptyList(),
    val message: String = "",
    val isFocused: Boolean = false,
)
