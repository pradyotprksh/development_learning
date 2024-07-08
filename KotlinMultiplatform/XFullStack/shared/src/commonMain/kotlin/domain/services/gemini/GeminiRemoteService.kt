package domain.services.gemini

import core.models.request.GrokConversation
import core.models.response.GeminiResponse

interface GeminiRemoteService {
    suspend fun getTweetEmotion(value: String, apiKey: String): GeminiResponse?

    suspend fun getHumanNature(value: String, apiKey: String): GeminiResponse?

    suspend fun getGrokReply(
        value: String,
        pastGrokConversation: List<GrokConversation>,
        apiKey: String,
    ): GeminiResponse?
}