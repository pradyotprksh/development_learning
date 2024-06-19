package com.pradyotprakash.xfullstack.data.chat

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Chat(
    @BsonId val id: ObjectId = ObjectId(),
    val users: List<ObjectId>,
    val createdOn: Long,
    val createdBy: ObjectId,
    val isGroup: Boolean,
    val isDirect: Boolean,
)
