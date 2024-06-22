package com.pradyotprakash.xfullstack.data.chat

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.`in`
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import utils.Constants.Database.Collections.CHAT
import utils.Constants.DbKeys.ID
import utils.Constants.DbKeys.USERS

class MongoChatDataSource(
    db: MongoDatabase,
) : ChatDataSource {
    private val chatCollection = db.getCollection<Chat>(CHAT)
    override suspend fun addChat(chat: Chat): Boolean {
        return chatCollection.insertOne(chat).wasAcknowledged()
    }

    override suspend fun chatDetails(id: String): Chat? {
        return chatCollection.find(
            Filters.eq(ID, ObjectId(id)),
        ).firstOrNull()
    }

    override suspend fun chatDetailsByUsers(users: List<ObjectId>): Chat? {
        return chatCollection.find(
            Filters.and(
                Filters.size(
                    USERS,
                    users.size,
                ),
                Filters.all(
                    USERS,
                    users,
                ),
            )
        ).firstOrNull()
    }

    override suspend fun getChats(userId: String): List<Chat> {
        return chatCollection.find(
            `in`(
                USERS,
                ObjectId(userId),
            ),
        ).toList()
    }
}