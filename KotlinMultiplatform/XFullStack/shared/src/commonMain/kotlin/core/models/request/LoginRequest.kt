package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String?,
    val phoneNumber: String?,
    val emailAddress: String?,
    val password: String,
)