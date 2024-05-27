package data.response

import kotlinx.serialization.Serializable

@Serializable
data class PollChoicesResponse(
    val id: String,
    val choice: String,
    val voteCount: Int,
)
