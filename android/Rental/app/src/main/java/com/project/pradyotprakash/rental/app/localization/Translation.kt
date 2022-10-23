package com.project.pradyotprakash.rental.app.localization

import android.content.Context
import com.orhanobut.logger.Logger
import com.project.pradyotprakash.rental.app.utils.Assets
import com.project.pradyotprakash.rental.app.utils.Constants.defaultLanguage
import org.json.JSONObject

/**
 * A translation object which will be used to handle the basic translation
 * functionalities required across the application like fetching the translations,
 * get the values, etc.
 */
object Translation {
    private lateinit var translationJSON: JSONObject

    /**
     * Update the localization map with the language code
     *
     * @param lan_key Language key to be used
     */
    fun updateLocalizationMap(lan_key: String = defaultLanguage, context: Context) {
        try {
            val inputSystem = context.assets.open(Assets.Localization(lanKey = lan_key).path)
            val buffer = ByteArray(inputSystem.available())
            inputSystem.read(buffer)
            inputSystem.close()
            val json = String(buffer, Charsets.UTF_8)
            translationJSON = JSONObject(json)
        } catch (e: Exception) {
            Logger.e(e.toString())
        }
    }

    /**
     * Get the value from the translation
     *
     * @param key Key to be used to get the value
     */
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