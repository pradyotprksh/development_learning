package domain.repositories.gemini

import core.models.request.GrokConversation
import domain.services.gemini.GeminiRemoteService
import utils.Constants.ConstValues.ENABLE_GEMINI
import utils.Localization

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

    override suspend fun getGrokReply(
        value: String,
        pastGrokConversation: List<GrokConversation>,
        apiKey: String,
    ) = try {
        if (!ENABLE_GEMINI) {
            listOf(Localization.GROK_NOT_AVAILABLE)
        } else {
            val response = geminiRemoteService.getGrokReply(value, pastGrokConversation, apiKey)
            response?.grokReply ?: listOf(Localization.GROK_NOT_AVAILABLE)
        }
    } catch (_: Exception) {
        listOf(Localization.GROK_NOT_AVAILABLE)
    }
}