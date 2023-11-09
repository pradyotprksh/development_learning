package com.pradyotprakash.libraryowner.domain.usecases

import com.pradyotprakash.libraryowner.core.response.OwnerResponse
import com.pradyotprakash.libraryowner.domain.repositories.IpGeolocationRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IpGeolocationUseCase @Inject constructor(
    private val ipGeolocationRepository: IpGeolocationRepository
) {
    suspend fun getGeolocationDetails() = flow {
        emit(OwnerResponse.Loading)
        emit(ipGeolocationRepository.getGeolocationDetails())
        emit(OwnerResponse.Idle)
    }
}