package com.pradyotprakash.findingfalcone.data.services

import com.pradyotprakash.findingfalcone.domain.entity.Vehicles
import retrofit2.Response
import retrofit2.http.GET

interface VehiclesService {
    @GET("/vehicles")
    suspend fun getVehicles(): Response<Vehicles>
}