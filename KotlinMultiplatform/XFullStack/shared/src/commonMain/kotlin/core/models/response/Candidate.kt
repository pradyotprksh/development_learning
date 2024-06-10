package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class Candidate(
    val content: Content,
    val finishReason: String,
    val index: Long,
    val safetyRatings: List<SafetyRating>
)