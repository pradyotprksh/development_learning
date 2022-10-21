package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.SearchRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend fun performSearchQuery(
        appCheckToken: String,
        searchedText: String,
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            searchRepository.performSearchQuery(
                appCheckToken = appCheckToken,
                searchedText = searchedText,
            )
        )
        emit(RenterResponse.Idle)
    }
}