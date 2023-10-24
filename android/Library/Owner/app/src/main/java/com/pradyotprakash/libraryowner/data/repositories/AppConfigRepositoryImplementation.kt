package com.pradyotprakash.libraryowner.data.repositories

import com.pradyotprakash.libraryowner.data.services.appconfig.AppConfigService
import com.pradyotprakash.libraryowner.domain.repositories.AppConfigRepository
import kotlinx.coroutines.flow.Flow

class AppConfigRepositoryImplementation(
    private val appConfigService: AppConfigService,
) : AppConfigRepository {
    override fun getCurrentLanguage(): Flow<String> = appConfigService.getCurrentLanguage()
}