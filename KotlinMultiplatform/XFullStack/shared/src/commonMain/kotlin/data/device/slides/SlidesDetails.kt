package data.device.slides

import kotlinx.serialization.Serializable

@Serializable
data class SlidesDetails(
    val title: String = "",
    val showKeyMap: Boolean = false,
    val workOnAllPlatforms: Boolean = false,
    val allowSlideChangeOnTap: Boolean = false,
    val showBackButton: Boolean = false,
    val showTitle: Boolean = false,
    val slides: List<Slides> = emptyList(),
)

