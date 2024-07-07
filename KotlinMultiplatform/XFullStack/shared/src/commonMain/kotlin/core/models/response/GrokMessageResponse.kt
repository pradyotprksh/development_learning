package core.models.response

data class GrokMessageResponse(
    val id: String,
    val chatId: String,
    val message: String,
    val messageOn: Long,
    val role: String,
)