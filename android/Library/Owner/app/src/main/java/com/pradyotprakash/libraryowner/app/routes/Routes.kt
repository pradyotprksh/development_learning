package com.pradyotprakash.libraryowner.app.routes

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
    Welcome("welcome/")
}