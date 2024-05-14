package com.pradyotprakash.xfullstack.data.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
) {
    fun isValid() = username.isNotBlank() && password.isNotBlank()

    fun isUsernameValid() = username.length >= 4

    fun isPasswordValid() = password.length >= 8
}