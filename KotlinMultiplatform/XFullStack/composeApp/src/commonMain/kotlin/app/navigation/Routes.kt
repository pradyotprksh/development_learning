package app.navigation

import utils.Constants.Keys.VALUE

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
    OtpVerification("/otp-verification/{$VALUE}"),
}