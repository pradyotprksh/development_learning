package com.pradyotprakash.xfullstack.data.message

interface MessageDataSource {
    suspend fun addMessage(message: Message): Boolean

    suspend fun messageDetails(id: String): Message?

    suspend fun getMessages(chatId: String): List<Message>
}