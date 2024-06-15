package com.pradyotprakash.xfullstack.data.bookmark

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.BOOKMARKED_AT
import utils.Constants.DbKeys.TWEET_ID
import utils.Constants.DbKeys.USER_ID

data class Bookmark(
    @BsonId val id: ObjectId = ObjectId(),
    @BsonProperty(TWEET_ID) val tweetId: ObjectId,
    @BsonProperty(USER_ID) val userId: ObjectId,
    @BsonProperty(BOOKMARKED_AT) val bookmarkedAt: Long,
)
