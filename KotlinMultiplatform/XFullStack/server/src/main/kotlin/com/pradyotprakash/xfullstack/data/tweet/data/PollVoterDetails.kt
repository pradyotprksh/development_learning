package com.pradyotprakash.xfullstack.data.tweet.data

import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.VOTED_BY
import utils.Constants.DbKeys.VOTED_ON

data class PollVoterDetails(
    @BsonProperty(VOTED_BY) val votedBy: ObjectId,
    @BsonProperty(VOTED_ON) val votedOn: Long,
)
