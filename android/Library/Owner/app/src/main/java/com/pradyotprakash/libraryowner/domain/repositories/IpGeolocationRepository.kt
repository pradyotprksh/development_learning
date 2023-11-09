package com.pradyotprakash.libraryowner.domain.repositories

import com.pradyotprakash.libraryowner.core.models.IpGeolocationDetails
import com.pradyotprakash.libraryowner.core.response.OwnerResponse

interface IpGeolocationRepository {
    suspend fun getGeolocationDetails(): OwnerResponse<out IpGeolocationDetails>
}