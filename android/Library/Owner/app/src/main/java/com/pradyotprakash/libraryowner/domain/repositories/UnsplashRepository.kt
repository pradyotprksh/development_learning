package com.pradyotprakash.libraryowner.domain.repositories

import com.pradyotprakash.libraryowner.core.models.UnsplashImage
import com.pradyotprakash.libraryowner.core.response.OwnerResponse

interface UnsplashRepository {
    fun getImageFromUnsplash(
        query: String,
        perPage: Int,
        orientation: String
    ): OwnerResponse<out UnsplashImage>
}