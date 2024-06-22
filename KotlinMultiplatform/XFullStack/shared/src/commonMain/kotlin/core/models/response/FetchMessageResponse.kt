package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class FetchMessageResponse(
    val chat: ChatResponse,
    val messages: List<MessageResponse>,
)
