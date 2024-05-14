package com.pradyotprakash.xfullstack.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String,
    val password: String,
    val bio: String?,
    val emailAddress: String,
    val phoneNumber: String,
    val profilePicture: String?,
    val dateOfBirth: String
)
