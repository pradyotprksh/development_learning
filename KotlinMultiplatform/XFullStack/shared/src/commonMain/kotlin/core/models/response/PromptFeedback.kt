package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class PromptFeedback(
    val safetyRatings: List<SafetyRating>
)