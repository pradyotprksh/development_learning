package com.pradyotprakash.xfullstack.data.message

import com.mongodb.kotlin.client.coroutine.MongoDatabase
import utils.Constants.Database.Collections.MESSAGE

class MongoMessageDataSource(
    db: MongoDatabase,
) : MessageDataSource {
    private val messageCollection = db.getCollection<Message>(MESSAGE)
}