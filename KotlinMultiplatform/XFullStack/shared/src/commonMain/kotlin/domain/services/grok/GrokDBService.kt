package domain.services.grok

import core.models.realm.GrokChatDB
import core.models.realm.GrokMessageDB
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface GrokDBService {
    fun getAllGrokChats(): Flow<ResultsChange<GrokChatDB>>

    fun getAllConversation(chatId: String): Flow<ResultsChange<GrokMessageDB>>

    suspend fun updateConversation(chatId: String, grokMessageDB: GrokMessageDB)
}