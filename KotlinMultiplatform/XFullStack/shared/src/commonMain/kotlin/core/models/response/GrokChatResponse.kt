package core.models.response

data class GrokChatResponse(
    val chatId: String,
    val chatTitle: String,
    val isArchived: Boolean,
    val createdOn: Long,
    val lastMessageOn: Long,
    val messages: List<GrokMessageResponse>,
)
