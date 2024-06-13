package app.composables.userDrawer.state

data class UserDrawerState(
    val profileImage: String? = null,
    val userId: String? = null,
    val name: String = "",
    val username: String = "",
    val following: Int = 0,
    val followers: Int = 0,
)
