package com.pradyotprakash.xfullstack.data.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

/**
 * User data class
 */
data class User(
    @BsonId val id: ObjectId = ObjectId(),
    val username: String,
    val password: String,
    val salt: String,
    val emailAddress: String,
    val phoneNumber: String,
    val profilePicture: String?,
    val bio: String?,
    val gender: String,
    val dateOfBirth: String,
    val location: String
)
