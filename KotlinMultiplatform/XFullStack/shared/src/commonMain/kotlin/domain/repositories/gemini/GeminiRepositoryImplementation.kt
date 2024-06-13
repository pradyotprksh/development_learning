package domain.repositories.gemini

import domain.services.gemini.GeminiRemoteService

class GeminiRepositoryImplementation(
    private val geminiRemoteService: GeminiRemoteService,
) : GeminiRepository {
    override suspend fun getTweetEmotion(value: String, apiKey: String) = try {
        val response = geminiRemoteService.getTweetEmotion(value, apiKey)
        response?.response ?: emptyList()
    } catch (_: Exception) {
        emptyList()
    }

    override suspend fun getHumanNature(value: String, apiKey: String) = try {
        val response = geminiRemoteService.getHumanNature(value, apiKey)
        response?.response ?: emptyList()
    } catch (_: Exception) {
        emptyList()
    }
}