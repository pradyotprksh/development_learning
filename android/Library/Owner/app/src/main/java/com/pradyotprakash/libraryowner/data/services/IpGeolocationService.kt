package com.pradyotprakash.libraryowner.data.services

import com.pradyotprakash.libraryowner.core.models.IpGeolocationDetails
import retrofit2.Response
import retrofit2.http.GET

interface IpGeolocationService {
    @GET("/ipgeo")
    suspend fun getCurrentIpDetails(): Response<IpGeolocationDetails>
}