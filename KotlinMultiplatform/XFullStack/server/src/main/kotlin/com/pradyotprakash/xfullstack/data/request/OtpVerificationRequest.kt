package com.pradyotprakash.xfullstack.data.request

import kotlinx.serialization.Serializable

@Serializable
data class OtpVerificationRequest(
    val value: String,
    val otp: String,
)
