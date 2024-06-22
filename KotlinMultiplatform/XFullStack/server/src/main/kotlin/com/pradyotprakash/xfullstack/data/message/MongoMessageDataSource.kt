package com.pradyotprakash.xfullstack.data.message

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import utils.Constants.Database.Collections.MESSAGE
import utils.Constants.DbKeys.CHAT_ID
import utils.Constants.DbKeys.ID
import utils.Constants.DbKeys.MESSAGE_ON

class MongoMessageDataSource(
    db: MongoDatabase,
) : MessageDataSource {
    private val messageCollection = db.getCollection<Message>(MESSAGE)
    override suspend fun addMessage(message: Message): Boolean {
        return messageCollection.insertOne(message).wasAcknowledged()
    }

    override suspend fun messageDetails(id: String): Message? {
        return messageCollection.find(
            Filters.eq(ID, ObjectId(id)),
        ).firstOrNull()
    }

    override suspend fun getMessages(chatId: String): List<Message> {
        return messageCollection.find(
            Filters.eq(CHAT_ID, ObjectId(chatId))
        ).sort(Sorts.descending(MESSAGE_ON)).toList()
    }
}