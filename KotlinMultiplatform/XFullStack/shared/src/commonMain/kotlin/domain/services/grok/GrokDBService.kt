package domain.services.grok

import core.models.realm.GrokChatDB
import core.models.realm.GrokMessageDB
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface GrokDBService {
    fun getAllGrokChats(): Flow<ResultsChange<GrokChatDB>>

    fun listenToConversation(chatId: String): Flow<ResultsChange<GrokMessageDB>>

    fun getAllConversation(chatId: String): List<GrokMessageDB>

    suspend fun createChat(chatId: String, chatTitle: String, createdOn: Long)

    suspend fun updateConversation(chatId: String, grokMessageDB: GrokMessageDB)
}