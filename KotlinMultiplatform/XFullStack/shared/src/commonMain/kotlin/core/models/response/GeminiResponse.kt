package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate>,
) {
    val response: List<String>
        get() = candidates.map { can -> can.content.parts.map { part -> part.text } }.flatten()
            .joinToString().lowercase().split(",").asSequence()
            .map { removeSpaces -> removeSpaces.trim() }.toSet().toMutableList()
            .filter { removeSentence -> removeSentence.isNotEmpty() && !removeSentence.contains(" ") }
            .map { removeFormatters ->
                removeFormatters.filterNot { removeSpecialCharacters ->
                        !removeSpecialCharacters.isLetterOrDigit() && removeSpecialCharacters.isWhitespace()
                    }
            }.toList()
}

