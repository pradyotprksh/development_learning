package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class GrokConversation(
    val role: String,
    val prompt: String,
)