package core.models.response

data class GrokResponse(
    val chatId: String,
    val response: List<String>,
    val tweetResponse: List<TweetResponse>,
    val chatResponse: List<ChatResponse>,
    val messageResponse: List<MessageResponse>,
    val userInfoResponse: List<UserInfoResponse>,
)
