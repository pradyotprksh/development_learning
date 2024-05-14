package com.pradyotprakash.xfullstack.data.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)