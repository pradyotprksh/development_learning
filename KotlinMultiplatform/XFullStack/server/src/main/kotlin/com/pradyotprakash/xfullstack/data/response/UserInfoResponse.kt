package com.pradyotprakash.xfullstack.data.response

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val id: String,
    val username: String,
    val bio: String?,
    val emailAddress: String?,
    val phoneNumber: String?,
    val profilePicture: String?,
    val dateOfBirth: String
)
