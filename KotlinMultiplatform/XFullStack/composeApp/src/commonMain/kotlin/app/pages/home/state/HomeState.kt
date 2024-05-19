package app.pages.home.state

data class HomeState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
)