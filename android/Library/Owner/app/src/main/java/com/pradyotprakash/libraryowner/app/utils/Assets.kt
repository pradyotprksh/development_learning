package com.pradyotprakash.libraryowner.app.utils

import com.pradyotprakash.libraryowner.R
import com.pradyotprakash.libraryowner.app.utils.Constants.defaultLanguage

sealed class Assets(
    val resourceId: Int = -1,
    val path: String = "",
    val imageDescription: String = ""
) {
    object AppIcon :
        Assets(resourceId = R.drawable.app_icon, imageDescription = "Main application icon")

    object DefaultProfileImage :
        Assets(resourceId = R.drawable.default_profile_image, imageDescription = "Default profile image")

    data class Localization(val lanKey: String = defaultLanguage) :
        Assets(path = "localization_${lanKey}.json")
}