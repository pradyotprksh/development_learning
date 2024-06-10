package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class OTPResponse(
    val otp: String,
)
