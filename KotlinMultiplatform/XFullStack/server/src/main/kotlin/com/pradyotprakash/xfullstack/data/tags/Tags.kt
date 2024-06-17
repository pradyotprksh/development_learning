package com.pradyotprakash.xfullstack.data.tags

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import utils.Constants.DbKeys.COUNT

data class Tags(
    @BsonId val id: String,
    @BsonProperty(COUNT) val count: Int,
)