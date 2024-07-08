package data.device.grok

import core.models.realm.GrokChatDB
import core.models.realm.GrokMessageDB
import domain.services.grok.GrokDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.CHAT_ID
import utils.Constants.DbKeys.LAST_MESSAGE_ON
import utils.Constants.DbKeys.MESSAGE_ON

class GrokDBServiceImplementation(
    private val realm: Realm,
) : GrokDBService {
    override fun getAllGrokChats(): Flow<ResultsChange<GrokChatDB>> {
        return realm.query<GrokChatDB>().sort(
            property = LAST_MESSAGE_ON,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override fun getAllConversation(chatId: String): Flow<ResultsChange<GrokMessageDB>> {
        return realm.query<GrokMessageDB>(
            "$CHAT_ID == $0", chatId
        ).sort(
            property = MESSAGE_ON,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override suspend fun createChat(chatId: String, chatTitle: String, createdOn: Long) {
        realm.write {
            val unmanagedObject = GrokChatDB().apply {
                this.chatId = chatId
                this.chatTitle = chatTitle
                this.isArchived = false
                this.createdOn = createdOn
            }
            copyToRealm(
                unmanagedObject,
            )
        }
    }

    override suspend fun updateConversation(chatId: String, grokMessageDB: GrokMessageDB) {
        realm.write {
            query<GrokChatDB>(
                "$CHAT_ID == $0", chatId
            ).find().first().apply {
                messages.add(grokMessageDB)
                lastMessageOn = grokMessageDB.messageOn
            }
        }
    }
}