package com.pradyotprakash.xfullstack.data.message

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Message(
    @BsonId val id: ObjectId = ObjectId(),
    val chatId: ObjectId,
    val messageBy: ObjectId,
    val messageTo: ObjectId,
    val messageOn: Long,
    val isRead: Boolean,
    val media: List<String>,
    val gif: List<String>,
    val replyTo: ObjectId?,
    val callMessage: Boolean,
    val videoMessage: Boolean,
    val isForwardMessage: Boolean,
    val reaction: List<String>,
    val isDirectMessage: Boolean,
    val isGroupMessage: Boolean,
    val audio: String,
)
