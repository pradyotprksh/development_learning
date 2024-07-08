package domain.repositories.gemini

import core.models.request.GrokConversation

interface GeminiRepository {
    suspend fun getTweetEmotion(value: String, apiKey: String): List<String>

    suspend fun getHumanNature(value: String, apiKey: String): List<String>

    suspend fun getGrokReply(
        value: String,
        pastGrokConversation: List<GrokConversation>,
        apiKey: String,
    ): List<String>
}