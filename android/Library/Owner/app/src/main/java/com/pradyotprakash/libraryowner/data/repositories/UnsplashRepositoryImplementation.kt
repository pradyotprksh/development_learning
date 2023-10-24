package com.pradyotprakash.libraryowner.data.repositories

import com.pradyotprakash.libraryowner.app.localization.TR
import com.pradyotprakash.libraryowner.core.response.OwnerException
import com.pradyotprakash.libraryowner.core.response.OwnerResponse
import com.pradyotprakash.libraryowner.core.response.parseResponse
import com.pradyotprakash.libraryowner.data.services.UnsplashService
import com.pradyotprakash.libraryowner.domain.repositories.UnsplashRepository

class UnsplashRepositoryImplementation(
    private val unsplashService: UnsplashService,
) : UnsplashRepository {
    override suspend fun getImageFromUnsplash(query: String, perPage: Int, orientation: String) =
        try {
            unsplashService.searchPhotos(query, perPage, orientation).parseResponse()
        } catch (e: Exception) {
            OwnerResponse.Error(
                OwnerException(message = e.localizedMessage ?: e.message ?: TR.noDataFoundError)
            )
        }
}