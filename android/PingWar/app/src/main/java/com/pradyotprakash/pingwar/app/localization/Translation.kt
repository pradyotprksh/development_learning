package com.pradyotprakash.pingwar.app.localization

import android.content.Context
import com.orhanobut.logger.Logger
import com.pradyotprakash.pingwar.app.utils.Assets
import com.pradyotprakash.pingwar.app.utils.Constants.defaultLanguage
import org.json.JSONObject

object Translation {
    private lateinit var translationJSON: JSONObject

    fun updateLocalizationMap(lanKey: String = defaultLanguage, context: Context) {
        try {
            val inputSystem = context.assets.open(Assets.Localization(lanKey = lanKey).path)
            val buffer = ByteArray(inputSystem.available())
            inputSystem.read(buffer)
            inputSystem.close()
            val json = String(buffer, Charsets.UTF_8)
            translationJSON = JSONObject(json)
        } catch (e: Exception) {
            Logger.e(e.toString())
        }
    }

    fun getString(key: String): String {
        return if (this::translationJSON.isInitialized) {
            try {
                translationJSON.getString(key)
            } catch (e: Exception) {
                Logger.e(e.toString())
                key
            }
        } else {
            key
        }
    }
}