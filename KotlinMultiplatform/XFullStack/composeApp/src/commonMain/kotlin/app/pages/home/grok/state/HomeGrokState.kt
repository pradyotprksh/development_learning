package app.pages.home.grok.state

import core.models.request.Conversation

data class HomeGrokState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val conversation: List<Conversation> = emptyList(),
)
