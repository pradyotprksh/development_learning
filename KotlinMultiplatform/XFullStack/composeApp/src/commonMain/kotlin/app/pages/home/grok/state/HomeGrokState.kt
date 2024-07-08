package app.pages.home.grok.state

import core.models.request.GrokConversation

data class HomeGrokState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val grokConversation: List<GrokConversation> = emptyList(),
)
