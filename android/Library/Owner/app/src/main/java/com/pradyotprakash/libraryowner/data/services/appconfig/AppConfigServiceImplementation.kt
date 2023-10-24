package com.pradyotprakash.libraryowner.data.services.appconfig

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.pradyotprakash.libraryowner.app.utils.Constants.defaultLanguage
import com.pradyotprakash.libraryowner.data.services.appconfig.AppConfigService.PreferencesKey.PREF_LANGUAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppConfigServiceImplementation(
    private val dataStore: DataStore<Preferences>
) : AppConfigService {
    companion object {
        const val PREFS_APP_CONFIG = "app_config_prefs"
    }

    override fun getCurrentLanguage(): Flow<String> = dataStore.data.map {
        it[PREF_LANGUAGE] ?: defaultLanguage
    }
}