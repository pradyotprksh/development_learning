package app.pages.home.grok.state

import core.models.response.GrokChatResponse
import core.models.response.GrokMessageResponse

data class HomeGrokState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val grokChats: List<GrokChatResponse> = emptyList(),
    val currentChat: GrokChatResponse? = null,
    val selectedChatId: String = "",
    val prompt: String = "",
    val profileImage: String? = "",
) {
    val selectedIndex: Int
        get() {
            val index = grokChats.indexOfFirst { it.chatId == selectedChatId }
            return if (index < 0) 0 else index + 1
        }

    val conversations: List<GrokMessageResponse>
        get() = (grokChats.find { it.chatId == selectedChatId }?.messages
            ?: emptyList()).asReversed()
}
