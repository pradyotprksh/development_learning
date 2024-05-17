package data.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
    val userId: String,
    val token: String,
)
