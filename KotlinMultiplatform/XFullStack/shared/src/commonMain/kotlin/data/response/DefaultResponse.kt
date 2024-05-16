package data.response

import kotlinx.serialization.Serializable

@Serializable
data class DefaultResponse(
    val message: String,
)
