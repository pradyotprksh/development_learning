package com.pradyotprakash.postscomments.app.utils

import com.pradyotprakash.postscomments.R
import com.pradyotprakash.postscomments.app.utils.Constants.defaultLanguage

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

    data class Localization(val lanKey: String = defaultLanguage) :
        Assets(path = "localization_${lanKey}.json")
}