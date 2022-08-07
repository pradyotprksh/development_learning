package com.project.pradyotprakash.rental.app.localization

import android.content.Context
import com.project.pradyotprakash.rental.app.utils.Assets
import com.project.pradyotprakash.rental.app.utils.Constants
import org.json.JSONObject

/**
 * A translation object which will be used to handle the basic translation
 * functionalities required across the application like fetching the translations,
 * get the values, etc.
 */
object Translation {
    private lateinit var translationJSON: JSONObject

    fun updateLocalizationMap(lan_key: String = Constants.defaultLanguage, context: Context) {
        val inputSystem = context.assets.open(Assets.Localization(lanKey = lan_key).path)
        val buffer = ByteArray(inputSystem.available())
        inputSystem.read(buffer)
        inputSystem.close()
        val json = String(buffer, Charsets.UTF_8)
        translationJSON = JSONObject(json)
    }

    fun getString(key: String): String {
        return try {
            translationJSON.getString(key)
        } catch (e: Exception) {
            key
        }
    }
}