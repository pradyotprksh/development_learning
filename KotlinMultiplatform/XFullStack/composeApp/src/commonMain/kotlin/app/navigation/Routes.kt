package app.navigation

/**
 * Routes
 */
enum class Routes(
    val route: String,
) {
    Splash("/splash"),
    AuthenticationOption("/authentication-option"),
    Login("/login"),
    Register("/register"),
}