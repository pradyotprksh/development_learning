package com.pradyotprakash.xfullstack.data.tweets

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.CREATED_BY
import utils.Constants.DbKeys.CREATED_ON

data class Tweets(
    @BsonId val id: ObjectId = ObjectId(),
    @BsonProperty(CREATED_BY) val createdBy: String,
    @BsonProperty(CREATED_ON) val createdOn: Long,
)
