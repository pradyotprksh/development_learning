package core.navigator

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

/**
 * A list of all the routes in the whole application
 */
enum class Routes(
    val route: String,
    val arguments: List<String> = emptyList()
) {
    Splash("splash/"),
}