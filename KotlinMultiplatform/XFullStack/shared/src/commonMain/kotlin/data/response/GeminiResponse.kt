package data.response

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate>, val promptFeedback: PromptFeedback
)

