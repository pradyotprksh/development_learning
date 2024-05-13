package com.pradyotprakash.xfullstack.data.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

/**
 * User data class
 */
data class User(
    @BsonId val id: ObjectId = ObjectId(),
    val username: String,
    val password: String,
    val salt: String,
    val bio: String?,
    @BsonProperty("email_address") val emailAddress: String,
    @BsonProperty("phone_number") val phoneNumber: String,
    @BsonProperty("profile_picture") val profilePicture: String?,
    @BsonProperty("date_of_birth") val dateOfBirth: String
)
