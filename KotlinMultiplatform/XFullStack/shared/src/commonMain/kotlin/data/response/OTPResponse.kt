package data.response

import kotlinx.serialization.Serializable

@Serializable
data class OTPResponse(
    val otp: String,
    val message: String,
)
