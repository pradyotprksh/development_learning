package domain.repositories.gemini

interface GeminiRepository {
    suspend fun getTweetEmotion(value: String, apiKey: String): List<String>

    suspend fun getHumanNature(value: String, apiKey: String): List<String>
}