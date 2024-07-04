package domain.repositories.gemini

import core.models.request.Conversation

interface GeminiRepository {
    suspend fun getTweetEmotion(value: String, apiKey: String): List<String>

    suspend fun getHumanNature(value: String, apiKey: String): List<String>

    suspend fun getGrokReply(
        value: String,
        pastConversation: List<Conversation>,
        apiKey: String,
    ): List<String>
}