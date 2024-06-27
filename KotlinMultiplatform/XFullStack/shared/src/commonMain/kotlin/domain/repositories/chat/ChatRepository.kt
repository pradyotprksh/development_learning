package domain.repositories.chat

import core.models.response.ChatResponse
import core.models.response.ClientResponse
import core.models.response.MessageResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun updateMessage(chatId: String): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun updateChats(): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun allChatsChanges(): Flow<List<ChatResponse>>

    suspend fun allMessagesChanges(chatId: String): Flow<List<MessageResponse>>

    suspend fun getChatChanges(chatId: String): Flow<ChatResponse?>
}