package app.pages.slides.state

import data.device.slides.SlidesDetails

data class SlidesState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val slidesDetails: SlidesDetails? = null,
)
