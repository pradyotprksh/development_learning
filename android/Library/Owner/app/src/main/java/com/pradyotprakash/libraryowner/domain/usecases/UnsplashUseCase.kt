package com.pradyotprakash.libraryowner.domain.usecases

import com.pradyotprakash.libraryowner.core.response.OwnerResponse
import com.pradyotprakash.libraryowner.domain.repositories.UnsplashRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UnsplashUseCase @Inject constructor(
    private var unsplashRepository: UnsplashRepository,
) {
    suspend fun getLibraryPortraitImage(count: Int) = flow {
        emit(OwnerResponse.Loading)
        emit(
            unsplashRepository.getImageFromUnsplash(
                query = "library",
                perPage = count,
                orientation = "portrait"
            )
        )
        emit(OwnerResponse.Idle)
    }
}