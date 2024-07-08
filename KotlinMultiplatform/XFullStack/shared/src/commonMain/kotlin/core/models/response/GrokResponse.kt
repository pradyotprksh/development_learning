package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class GrokResponse(
    val chatId: String,
    val response: String,
    val tweetResponse: List<TweetResponse>,
    val chatResponse: List<ChatResponse>,
    val messageResponse: List<MessageResponse>,
    val userInfoResponse: List<UserInfoResponse>,
)
