package data.device.slides

import kotlinx.serialization.Serializable

@Serializable
data class Subtitle(
    val value: String = "",
    val styles: Styles? = null,
)