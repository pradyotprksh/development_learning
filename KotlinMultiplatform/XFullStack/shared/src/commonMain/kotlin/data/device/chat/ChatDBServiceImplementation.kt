package data.device.chat

import core.models.realm.ChatResponseDB
import core.models.realm.MessageResponseDB
import core.models.response.ChatResponse
import core.models.response.MessageResponse
import core.parser.parseToChatResponseDB
import core.parser.parseToMessageResponseDB
import domain.services.chat.ChatDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.SingleQueryChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.CHAT_ID
import utils.Constants.DbKeys.CREATED_ON
import utils.Constants.DbKeys.MESSAGE_ON

class ChatDBServiceImplementation(
    private val realm: Realm,
) : ChatDBService {
    override suspend fun saveChatDetails(chatResponse: List<ChatResponse>): List<ChatResponseDB> =
        realm.write {
            val unmanagedObject = chatResponse.map { it.parseToChatResponseDB() }
            val managedObject = unmanagedObject.map {
                copyToRealm(
                    it, updatePolicy = UpdatePolicy.ALL
                )
            }
            managedObject
        }

    override suspend fun saveMessages(messagesResponse: List<MessageResponse>): List<MessageResponseDB> =
        realm.write {
            val unmanagedObject = messagesResponse.map { it.parseToMessageResponseDB() }
            val managedObject = unmanagedObject.map {
                copyToRealm(
                    it, updatePolicy = UpdatePolicy.ALL
                )
            }
            managedObject
        }

    override fun getChats(): Flow<ResultsChange<ChatResponseDB>> {
        return realm.query<ChatResponseDB>().sort(
            property = CREATED_ON,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override fun getAllMessages(chatId: String): Flow<ResultsChange<MessageResponseDB>> {
        return realm.query<MessageResponseDB>(
            "$CHAT_ID == $0", chatId
        ).sort(
            property = MESSAGE_ON,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override fun getChatChanges(chatId: String): Flow<SingleQueryChange<ChatResponseDB>> {
        return realm.query<ChatResponseDB>(
            "$CHAT_ID == $0", chatId
        ).first().asFlow()
    }
}