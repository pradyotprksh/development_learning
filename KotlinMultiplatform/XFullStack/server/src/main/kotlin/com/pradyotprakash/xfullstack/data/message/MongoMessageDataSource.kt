package com.pradyotprakash.xfullstack.data.message

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import org.bson.types.ObjectId
import utils.Constants.Database.Collections.MESSAGE
import utils.Constants.DbKeys.ID

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
}