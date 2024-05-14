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
) {
    fun isValid() = username.isNotBlank() && password.isNotBlank()

    fun isUsernameValid() = username.length >= 4

    fun isPasswordValid() = password.length >= 8
}
