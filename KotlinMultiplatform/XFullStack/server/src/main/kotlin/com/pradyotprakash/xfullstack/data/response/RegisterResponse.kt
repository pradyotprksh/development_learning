package com.pradyotprakash.xfullstack.data.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val token: String
)
