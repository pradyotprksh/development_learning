package com.pradyotprakash.xfullstack.data.chat

import org.bson.types.ObjectId

interface ChatDataSource {
    suspend fun addChat(chat: Chat): Boolean

    suspend fun chatDetails(id: String): Chat?

    suspend fun isChatAlreadyExists(users: List<ObjectId>): Boolean
}