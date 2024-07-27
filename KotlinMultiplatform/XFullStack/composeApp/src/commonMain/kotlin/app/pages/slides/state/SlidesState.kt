package app.pages.slides.state

import data.device.slides.SlidesDetails

data class SlidesState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val slidesDetails: SlidesDetails? = null,
    val keyEvent: Pair<String?, String?>? = null,
    val showKeyEvent: Boolean = false,
)
