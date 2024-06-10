package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class OtpVerificationRequest(
    val value: String,
    val otp: String,
)
