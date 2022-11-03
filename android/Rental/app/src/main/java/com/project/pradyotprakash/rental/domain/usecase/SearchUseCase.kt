package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.SearchRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend fun performSearchQuery(
        searchedText: String,
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            searchRepository.performSearchQuery(
                searchedText = searchedText,
            )
        )
        emit(RenterResponse.Idle)
    }

    suspend fun performLocationSearch(
        searchedText: String,
        zipCode: String,
        latitude: String,
        longitude: String,
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            searchRepository.performLocationSearch(
                searchedText = searchedText,
                zipCode = zipCode,
                latitude = latitude,
                longitude = longitude,
            )
        )
        emit(RenterResponse.Idle)
    }
}