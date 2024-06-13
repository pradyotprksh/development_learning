package com.pradyotprakash.xfullstack.data.follow

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.FOLLOWED_AT
import utils.Constants.DbKeys.FOLLOWER_ID
import utils.Constants.DbKeys.FOLLOWING_ID

data class Follow(
    @BsonId val id: ObjectId = ObjectId(),
    @BsonProperty(FOLLOWER_ID) val followerId: ObjectId,
    @BsonProperty(FOLLOWING_ID) val followingId: ObjectId,
    @BsonProperty(FOLLOWED_AT) val followedAt: Long,
)
