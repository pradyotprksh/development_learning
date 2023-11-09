package com.pradyotprakash.libraryowner.data.repositories

import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.core.models.IpGeolocationDetails
import com.pradyotprakash.libraryowner.core.response.OwnerException
import com.pradyotprakash.libraryowner.core.response.OwnerResponse
import com.pradyotprakash.libraryowner.core.response.parseResponse
import com.pradyotprakash.libraryowner.data.services.IpGeolocationService
import com.pradyotprakash.libraryowner.data.services.crashlytics.CrashlyticsService
import com.pradyotprakash.libraryowner.domain.repositories.IpGeolocationRepository

class IpGeolocationRepositoryImplementation(
    private val ipGeolocationService: IpGeolocationService,
    private val crashlyticsService: CrashlyticsService,
) : IpGeolocationRepository {
    override suspend fun getGeolocationDetails(): OwnerResponse<out IpGeolocationDetails> = try {
        ipGeolocationService.getCurrentIpDetails().parseResponse(crashlyticsService)
    } catch (e: Exception) {
        crashlyticsService.submitCaughtException(e)
        OwnerResponse.Error(
            OwnerException(message = e.localizedMessage ?: e.message ?: TR.noDataFoundError)
        )
    }
}