package core.models.request

import kotlinx.serialization.Serializable

@Serializable
data class ViewRequest(
    val viewedId: String,
)
