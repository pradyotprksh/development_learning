package com.pradyotprakash.xfullstack.data.tweet.data

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants

data class PollChoices(
    @BsonId val id: ObjectId = ObjectId(),
    val choice: String,
    @BsonProperty(Constants.DbKeys.VOTE_COUNT) val voteCount: Int,
)