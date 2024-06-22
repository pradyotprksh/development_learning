package domain.repositories.chat

import core.models.response.ClientResponse
import core.models.response.FetchMessageResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getMessages(chatId: String): Flow<ClientResponse<out XFullStackResponse<FetchMessageResponse>>>
}