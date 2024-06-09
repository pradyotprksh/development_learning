package data.response

import kotlinx.serialization.Serializable

@Serializable
data class PromptFeedback(
    val safetyRatings: List<SafetyRating>
)