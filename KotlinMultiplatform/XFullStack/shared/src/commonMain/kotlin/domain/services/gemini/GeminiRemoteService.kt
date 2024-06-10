package domain.services.gemini

import core.models.response.GeminiResponse

interface GeminiRemoteService {
    suspend fun getTweetEmotion(value: String, apiKey: String): GeminiResponse?
}