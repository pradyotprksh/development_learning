package com.project.pradyotprakash.rental.core.navigation

import com.project.pradyotprakash.rental.app.utils.ErrorScreenArguments
import com.project.pradyotprakash.rental.app.utils.InformationScreenArguments
import com.project.pradyotprakash.rental.app.utils.PropertyDetailsArguments
import com.project.pradyotprakash.rental.app.utils.UserDetailsArguments

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
    Option("option/"),
    Home("home/"),
    Welcome("welcome/"),
    Information(
        "information/",
        listOf(
            InformationScreenArguments.onlyPreview,
            InformationScreenArguments.allowBackOption,
            InformationScreenArguments.firstTimeAddingDetails,
        )
    ),
    Property("property/"),
    PropertyDetails(
        "property-details/",
        listOf(
            PropertyDetailsArguments.propertyId,
        )
    ),
    UserDetails(
        "user-details/",
        listOf(
            UserDetailsArguments.userId,
        )
    ),
    Error(
        "error/",
        listOf(
            ErrorScreenArguments.title,
            ErrorScreenArguments.subtitle,
            ErrorScreenArguments.description,
        )
    ),
    Search("search/")
}