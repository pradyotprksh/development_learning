package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class Part(
    val text: String,
)