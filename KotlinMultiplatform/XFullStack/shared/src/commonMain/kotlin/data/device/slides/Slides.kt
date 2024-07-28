package data.device.slides

import kotlinx.serialization.Serializable

@Serializable
data class Slides(
    val background: String = "",
    val title: Title? = null,
    val subtitle: Subtitle? = null,
    val footer: Footer? = null,
    val bottomImage: Image? = null,
)