package com.pradyotprakash.xfullstack.data.message

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.CHAT_ID
import utils.Constants.DbKeys.IS_DIRECT_MESSAGE
import utils.Constants.DbKeys.IS_FORWARD_MESSAGE
import utils.Constants.DbKeys.IS_GROUP_MESSAGE
import utils.Constants.DbKeys.IS_READ
import utils.Constants.DbKeys.MESSAGE
import utils.Constants.DbKeys.MESSAGE_BY
import utils.Constants.DbKeys.MESSAGE_ON
import utils.Constants.DbKeys.MESSAGE_TO
import utils.Constants.DbKeys.NOTIFICATION_MESSAGE
import utils.Constants.DbKeys.REPLY_MESSAGE_ID

data class Message(
    @BsonId val id: ObjectId = ObjectId(),
    @BsonProperty(CHAT_ID) val chatId: ObjectId,
    @BsonProperty(MESSAGE) val message: String,
    @BsonProperty(MESSAGE_BY) val messageBy: ObjectId,
    @BsonProperty(MESSAGE_TO) val messageTo: ObjectId,
    @BsonProperty(MESSAGE_ON) val messageOn: Long,
    @BsonProperty(IS_READ) val isRead: Boolean,
    val media: List<String>,
    val gif: List<String>,
    @BsonProperty(REPLY_MESSAGE_ID) val replyMessageId: ObjectId?,
    @BsonProperty(NOTIFICATION_MESSAGE) val notificationMessage: Boolean,
    @BsonProperty(IS_FORWARD_MESSAGE) val isForwardMessage: Boolean,
    val reaction: List<String>,
    @BsonProperty(IS_DIRECT_MESSAGE) val isDirectMessage: Boolean,
    @BsonProperty(IS_GROUP_MESSAGE) val isGroupMessage: Boolean,
    val audio: String,
)
