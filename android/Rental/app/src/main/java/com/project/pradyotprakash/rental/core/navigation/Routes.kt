package com.project.pradyotprakash.rental.core.navigation

import com.project.pradyotprakash.rental.app.utils.WelcomeScreenArguments

/**
 * A list of all the routes in the whole application
 */
fun Routes.path(): String {
    if (arguments.isEmpty()) return route
    var completePath = route
    for (argument in arguments) {
        completePath += "{${argument}}/"
    }
    return completePath.removeSuffix("/")
}

enum class Routes(
    val route: String,
    val arguments: List<String> = emptyList()
) {
    Splash("splash/"),
    Option("option/"),
    Welcome("welcome/", listOf(WelcomeScreenArguments.userType))
}