package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class TagsResponse(
    val tag: String,
    val totalTweets: Int,
    val count: Int,
    val isTrending: Boolean,
)
