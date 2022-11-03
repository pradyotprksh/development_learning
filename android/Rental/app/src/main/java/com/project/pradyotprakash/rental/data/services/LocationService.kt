package com.project.pradyotprakash.rental.data.services

import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import com.project.pradyotprakash.rental.domain.modal.LocationEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    @GET("/renter/location")
    suspend fun searchLocation(
        @Query("search_text") searchedText: String,
        @Query("zip_code") zipCode: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
    ): Response<DefaultEntity<List<LocationEntity>>>
}