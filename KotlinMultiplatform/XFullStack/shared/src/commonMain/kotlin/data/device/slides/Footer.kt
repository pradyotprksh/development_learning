package data.device.slides

import kotlinx.serialization.Serializable

@Serializable
data class Footer(
    val value: String = "",
    val styles: Styles? = null,
)