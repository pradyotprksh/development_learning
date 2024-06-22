package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class SendMessageResponse(
    val chatId: String,
)
