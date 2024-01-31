package com.pradyotprakash.pingwar.app.utils

import com.pradyotprakash.pingwar.R
import com.pradyotprakash.pingwar.app.utils.Constants.defaultLanguage

sealed class Assets(
    val resourceId: Int = -1,
    val path: String = "",
    val imageDescription: String = ""
) {
    data object AppIcon :
        Assets(resourceId = R.mipmap.ic_launcher, imageDescription = "Main application icon")

    data class Localization(val lanKey: String = defaultLanguage) :
        Assets(path = "localization_${lanKey}.json")
}