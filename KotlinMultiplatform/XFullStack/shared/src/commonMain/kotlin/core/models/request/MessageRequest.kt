package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(
    val chatId: String,
    val message: String,
    val messageTo: String,
    val users: List<String>,
    val media: List<String>,
    val gif: List<String>,
    val replyMessageId: String,
    val forwardMessageId: String,
    val reaction: String,
    val audio: String,
    val tweetId: String,
) {
    val isMessageOptional: Boolean
        get() = media.isNotEmpty() || gif.isNotEmpty() || forwardMessageId.isNotEmpty() || audio.isNotEmpty() || tweetId.isNotEmpty()
}
