package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class Conversation(
    val role: String,
    val prompt: String,
)