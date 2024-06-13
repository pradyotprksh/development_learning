package com.pradyotprakash.xfullstack.data.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import utils.Constants.DbKeys.DATE_OF_BIRTH
import utils.Constants.DbKeys.EMAIL_ADDRESS
import utils.Constants.DbKeys.PHONE_NUMBER
import utils.Constants.DbKeys.PROFILE_PICTURE

/**
 * User data class
 */
data class User(
    @BsonId val id: ObjectId = ObjectId(),
    val name: String,
    val username: String,
    val password: String,
    val salt: String,
    val bio: String?,
    @BsonProperty(EMAIL_ADDRESS) val emailAddress: String?,
    @BsonProperty(PHONE_NUMBER) val phoneNumber: String?,
    @BsonProperty(PROFILE_PICTURE) val profilePicture: String?,
    @BsonProperty(DATE_OF_BIRTH) val dateOfBirth: Long,
)
