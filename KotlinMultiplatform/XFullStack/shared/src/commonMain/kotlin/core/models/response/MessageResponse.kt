package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val id: String,
    val message: String,
    val messageOn: Long,
    val messageTime: String,
    val messageBy: String,
    val messageGroup: String,
    val isRead: Boolean,
    val media: List<String>,
    val gif: List<String>,
    val replyMessageDetails: MessageResponse?,
    val notificationMessage: Boolean,
    val forwardMessageDetails: MessageResponse?,
    val reaction: List<String>,
    val audio: String,
    val tweetDetails: TweetResponse?,
)
