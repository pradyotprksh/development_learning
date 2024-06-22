package app.pages.home.message.state

import core.models.response.ChatResponse

data class HomeMessageState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val chats: List<ChatResponse> = emptyList(),
)
