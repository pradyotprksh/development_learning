package com.pradyotprakash.findingfalcone.app.utils

import com.pradyotprakash.findingfalcone.R
import com.pradyotprakash.findingfalcone.app.utils.Constants.defaultLanguage

/**
 * An assets class which will hold the details of the assets used in the whole project.
 */
sealed class Assets(
    val resourceId: Int = -1,
    val path: String = "",
    val imageDescription: String = ""
) {
    object AppIcon :
        Assets(resourceId = R.drawable.app_icon, imageDescription = "Main application icon")

    object PlanetIcon1 : Assets(
        resourceId = R.drawable.planet_1,
        imageDescription = "Icon for planet"
    )

    object PlanetIcon2 : Assets(
        resourceId = R.drawable.planet_2,
        imageDescription = "Icon for planet"
    )

    object PlanetIcon3 : Assets(
        resourceId = R.drawable.planet_3,
        imageDescription = "Icon for planet"
    )

    object PlanetIcon4 : Assets(
        resourceId = R.drawable.planet_4,
        imageDescription = "Icon for planet"
    )

    object PlanetIcon5 : Assets(
        resourceId = R.drawable.planet_5,
        imageDescription = "Icon for planet"
    )

    object PlanetIcon6 : Assets(
        resourceId = R.drawable.planet_6,
        imageDescription = "Icon for planet"
    )

    data class Localization(val lanKey: String = defaultLanguage) :
        Assets(path = "localization_${lanKey}.json")
}

fun getPlanet(number: Int): Assets {
    return when (number) {
        1 -> Assets.PlanetIcon1
        2 -> Assets.PlanetIcon2
        3 -> Assets.PlanetIcon3
        4 -> Assets.PlanetIcon4
        5 -> Assets.PlanetIcon5
        6 -> Assets.PlanetIcon6
        else -> Assets.PlanetIcon1
    }
}