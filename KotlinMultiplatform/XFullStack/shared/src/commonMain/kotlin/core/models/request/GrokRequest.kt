package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class GrokRequest(
    val chatId: String,
    val prompt: String,
    val grokConversation: List<GrokConversation>,
)

