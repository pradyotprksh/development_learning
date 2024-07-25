package data.device.slides

import kotlinx.serialization.Serializable

@Serializable
data class Styles(
    val style: String? = null,
    val alignment: String? = null,
    val color: String? = null,
    val height: Float? = null,
    val width: Float? = null,
)