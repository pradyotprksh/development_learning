package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.WishlistRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WishlistUseCase @Inject constructor(
    private val wishlistRepository: WishlistRepository,
) {
    suspend fun createWishlist(
        userId: String,
        propertyId: String
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            wishlistRepository.createWishlist(
                userId = userId, propertyId = propertyId
            )
        )
        emit(RenterResponse.Idle)
    }

    suspend fun getWishlist(
        userId: String,
        propertyId: String = "",
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            wishlistRepository.getWishlist(
                userId = userId, propertyId = propertyId
            )
        )
        emit(RenterResponse.Idle)
    }

    suspend fun deleteWishlist(
        userId: String,
        propertyId: String,
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            wishlistRepository.deleteWishlist(
                userId = userId, propertyId = propertyId
            )
        )
        emit(RenterResponse.Idle)
    }
}