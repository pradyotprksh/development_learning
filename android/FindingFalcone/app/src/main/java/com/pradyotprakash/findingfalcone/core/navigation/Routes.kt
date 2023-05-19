package com.pradyotprakash.findingfalcone.core.navigation

/**
 * An extension on routes list which will combine the route
 * and all the arguments into one and return it.
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
    Selector("selector/"),
}