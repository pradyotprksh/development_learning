package domain.repositories.gemini

import domain.services.gemini.GeminiRemoteService

class GeminiRepositoryImplementation(
    private val geminiRemoteService: GeminiRemoteService,
) : GeminiRepository {
    override suspend fun getTweetEmotion(value: String, apiKey: String) = try {
        val response = geminiRemoteService.getTweetEmotion(value, apiKey)
        val text =
            response?.candidates?.map { can -> can.content.parts.map { part -> part.text } }
                ?.flatten()?.joinToString()?.lowercase()?.split(",")?.map { it.trim() }?.toSet()
                ?.toList()
        text ?: emptyList()
    } catch (_: Exception) {
        emptyList()
    }
}