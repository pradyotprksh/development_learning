package com.pradyotprakash.xfullstack.data.view.data

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.VIEWED_BY
import utils.Constants.DbKeys.VIEWED_ID
import utils.Constants.DbKeys.VIEWED_ON

data class View(
    @BsonId val id: ObjectId,
    @BsonProperty(VIEWED_ID) val viewedId: ObjectId,
    @BsonProperty(VIEWED_BY) val viewedBy: ObjectId,
    @BsonProperty(VIEWED_ON) val viewedOn: Long,
)
