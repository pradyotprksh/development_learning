package com.pradyotprakash.libraryowner.domain.repositories

import kotlinx.coroutines.flow.Flow

interface AppConfigRepository {
    fun getCurrentLanguage(): Flow<String>
}