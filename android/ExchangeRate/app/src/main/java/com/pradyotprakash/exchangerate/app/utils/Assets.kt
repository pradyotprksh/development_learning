package com.pradyotprakash.exchangerate.app.utils

import com.pradyotprakash.exchangerate.app.utils.Constants.defaultLanguage

sealed class Assets(
    val path: String = ""
) {
    data class Localization(val lanKey: String = defaultLanguage) :
        Assets(path = "localization_${lanKey}.json")

    data class Response(val fileName: String) :
        Assets(path = "${fileName}_success.json")
}