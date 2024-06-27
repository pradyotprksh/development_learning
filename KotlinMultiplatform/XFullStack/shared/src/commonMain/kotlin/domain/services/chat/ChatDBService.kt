package domain.services.chat

import core.models.realm.ChatResponseDB
import core.models.realm.MessageResponseDB
import core.models.response.ChatResponse
import core.models.response.MessageResponse
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.SingleQueryChange
import kotlinx.coroutines.flow.Flow

interface ChatDBService {
    suspend fun saveChatDetails(chatResponse: List<ChatResponse>): List<ChatResponseDB>

    suspend fun saveMessages(messagesResponse: List<MessageResponse>): List<MessageResponseDB>

    fun getChats(): Flow<ResultsChange<ChatResponseDB>>

    fun getAllMessages(chatId: String): Flow<ResultsChange<MessageResponseDB>>

    fun getChatChanges(chatId: String): Flow<SingleQueryChange<ChatResponseDB>>
}