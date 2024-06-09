package domain.repositories.gemini

import domain.services.gemini.GeminiRemoteService

class GeminiRepositoryImplementation(
    private val geminiRemoteService: GeminiRemoteService,
) : GeminiRepository {
    override suspend fun getTweetEmotion(value: String): List<String> {
        val response = geminiRemoteService.getTweetEmotion(value)
        return response?.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text?.lowercase()
            ?.split(",") ?: emptyList()
    }
}