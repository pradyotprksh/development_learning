package com.pradyotprakash.xfullstack.data.chat

import com.mongodb.kotlin.client.coroutine.MongoDatabase
import utils.Constants.Database.Collections.CHAT

class MongoChatDataSource(
    db: MongoDatabase,
) : ChatDataSource {
    private val chatCollection = db.getCollection<Chat>(CHAT)
}