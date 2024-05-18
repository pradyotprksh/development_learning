package app.navigation

import utils.Constants.ConstValues.USERNAME

/**
 * Routes
 */
enum class Routes(
    val route: String,
) {
    Splash("/splash"),
    AuthenticationOption("/authentication-option"),
    Login("/login/{$USERNAME}"),
    Register("/register"),
}