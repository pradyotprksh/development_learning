package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.data.services.WishlistService

class WishlistRepository(
    private val wishlistService: WishlistService,
    private val crashlytics: CrashlyticsService,
) {
    suspend fun createWishlist(
        userId: String,
        propertyId: String
    ) = wishlistService.createWishlist(
        userId = userId,
        propertyId = propertyId
    ).parseResponse(crashlytics)

    suspend fun getWishlist(
        userId: String,
        propertyId: String
    ) = wishlistService.getWishlist(
        userId = userId,
        propertyId = propertyId
    ).parseResponse(crashlytics)

    suspend fun deleteWishlist(
        userId: String,
        propertyId: String
    ) = wishlistService.deleteWishlist(
        userId = userId,
        propertyId = propertyId
    ).parseResponse(crashlytics)
}