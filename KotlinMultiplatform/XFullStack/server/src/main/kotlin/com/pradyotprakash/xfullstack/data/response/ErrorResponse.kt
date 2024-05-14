package com.pradyotprakash.xfullstack.data.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String
)
