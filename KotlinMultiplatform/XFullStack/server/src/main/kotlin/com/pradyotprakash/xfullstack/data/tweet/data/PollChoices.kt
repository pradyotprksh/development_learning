package com.pradyotprakash.xfullstack.data.tweet.data

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.VOTER_DETAILS
import utils.Constants.DbKeys.VOTE_COUNT

data class PollChoices(
    @BsonId val id: ObjectId = ObjectId(),
    val choice: String,
    @BsonProperty(VOTE_COUNT) val voteCount: Int,
    @BsonProperty(VOTER_DETAILS) val voterDetails: List<PollVoterDetails>,
)