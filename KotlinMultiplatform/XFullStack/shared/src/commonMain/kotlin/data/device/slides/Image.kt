package data.device.slides

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val value: String,
    val styles: Styles? = null,
)
