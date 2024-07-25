package data.device.slides

import kotlinx.serialization.Serializable

@Serializable
data class Title(
    val value: String = "",
    val styles: Styles? = null,
)