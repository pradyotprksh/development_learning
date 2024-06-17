package domain.repositories.gemini

import domain.services.gemini.GeminiRemoteService
import utils.Constants.ConstValues.ENABLE_GEMINI

class GeminiRepositoryImplementation(
    private val geminiRemoteService: GeminiRemoteService,
) : GeminiRepository {
    override suspend fun getTweetEmotion(value: String, apiKey: String) = try {
        if (!ENABLE_GEMINI) {
            emptyList()
        } else {
            val response = geminiRemoteService.getTweetEmotion(value, apiKey)
            response?.response ?: emptyList()
        }
    } catch (_: Exception) {
        emptyList()
    }

    override suspend fun getHumanNature(value: String, apiKey: String) = try {
        if (!ENABLE_GEMINI) {
            emptyList()
        } else {
            val response = geminiRemoteService.getHumanNature(value, apiKey)
            response?.response ?: emptyList()
        }
    } catch (_: Exception) {
        emptyList()
    }
}