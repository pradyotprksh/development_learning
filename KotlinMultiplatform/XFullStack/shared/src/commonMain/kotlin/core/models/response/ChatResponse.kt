package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class ChatResponse(
    val chatId: String,
    val users: List<String>,
    val createdOn: Long,
    val createdBy: String,
    val isGroup: Boolean,
    val isDirect: Boolean,
    val lastMessageDetails: MessageResponse?,
)
