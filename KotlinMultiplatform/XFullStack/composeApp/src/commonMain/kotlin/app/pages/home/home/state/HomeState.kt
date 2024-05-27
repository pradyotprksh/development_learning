package app.pages.home.home.state

data class HomeState(
    val showLoading: Boolean = false,
    val snackBarMessage: String? = null,
    val profileImage: String? = null,
    val name: String = "",
    val username: String = "",
    val following: Int = 0,
    val followers: Int = 0,
)