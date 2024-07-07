package domain.services.grok

import core.models.realm.GrokChatDB
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface GrokDBService {
    fun getAllGrokChats(): Flow<ResultsChange<GrokChatDB>>
}