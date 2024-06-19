package com.pradyotprakash.xfullstack.data.chat

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.CREATED_BY
import utils.Constants.DbKeys.CREATED_ON
import utils.Constants.DbKeys.IS_DIRECT
import utils.Constants.DbKeys.IS_GROUP
import utils.Constants.DbKeys.USERS

data class Chat(
    @BsonId val id: ObjectId = ObjectId(),
    @BsonProperty(USERS) val users: List<ObjectId>,
    @BsonProperty(CREATED_ON) val createdOn: Long,
    @BsonProperty(CREATED_BY) val createdBy: ObjectId,
    @BsonProperty(IS_GROUP) val isGroup: Boolean,
    @BsonProperty(IS_DIRECT) val isDirect: Boolean,
)
