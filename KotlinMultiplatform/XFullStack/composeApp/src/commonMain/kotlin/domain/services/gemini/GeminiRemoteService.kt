package domain.services.gemini

import data.response.GeminiResponse

interface GeminiRemoteService {
    suspend fun getTweetEmotion(value: String, apiKey: String): GeminiResponse?
}