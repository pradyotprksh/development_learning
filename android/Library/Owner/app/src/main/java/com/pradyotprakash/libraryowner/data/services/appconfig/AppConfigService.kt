package com.pradyotprakash.libraryowner.data.services.appconfig

import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

interface AppConfigService {
    fun getCurrentLanguage(): Flow<String>

    object PreferencesKey {
        val PREF_LANGUAGE = stringPreferencesKey("pref_language")
    }
}