package com.pradyotprakash.xfullstack.data.chat

import org.bson.types.ObjectId

interface ChatDataSource {
    suspend fun addChat(chat: Chat): Boolean

    suspend fun chatDetails(id: String): Chat?

    suspend fun chatDetailsByUsers(users: List<ObjectId>): Chat?
}