package com.pradyotprakash.notes.app.utils

import com.pradyotprakash.notes.R
import com.pradyotprakash.notes.app.utils.Constants.defaultLanguage

/**
 * An assets class which will hold the details of the assets used in the whole project.
 */
sealed class Assets(
    val resourceId: Int = -1,
    val path: String = "",
    val imageDescription: String = ""
) {
    object AppIcon :
        Assets(resourceId = R.drawable.ic_launcher, imageDescription = "Main application icon")

    data class Localization(val lanKey: String = defaultLanguage) :
        Assets(path = "localization_${lanKey}.json")
}