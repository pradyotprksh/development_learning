package domain.repositories.grok

import core.models.response.ClientResponse
import core.models.response.GrokChatResponse
import kotlinx.coroutines.flow.Flow

interface GrokRepository {
    suspend fun allGrokChangesChanges(): Flow<List<GrokChatResponse>>

    suspend fun sendPrompt(
        prompt: String,
        chatId: String,
    ): Flow<ClientResponse<out String>>
}