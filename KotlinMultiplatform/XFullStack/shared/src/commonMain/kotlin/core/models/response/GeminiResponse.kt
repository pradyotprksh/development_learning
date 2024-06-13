package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate>,
) {
    val response: List<String>
        get() = candidates.map { can -> can.content.parts.map { part -> part.text } }.flatten()
            .joinToString().lowercase().split(",").map { it.trim() }.toSet().toMutableList()
            .filter { it.isNotEmpty() && !it.contains(" ") }
}

