package com.pradyotprakash.libraryowner.domain.usecases

import com.pradyotprakash.libraryowner.domain.repositories.AppConfigRepository
import javax.inject.Inject

class AppConfigUseCase @Inject constructor(
    private val appConfigRepository: AppConfigRepository,
) {
    fun getCurrentLanguage() = appConfigRepository.getCurrentLanguage()
}