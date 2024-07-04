package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class GrokRequest(
    val prompt: String,
    val conversation: List<Conversation>,
)

