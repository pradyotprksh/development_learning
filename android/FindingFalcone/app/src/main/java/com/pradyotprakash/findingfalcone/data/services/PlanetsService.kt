package com.pradyotprakash.findingfalcone.data.services

import com.pradyotprakash.findingfalcone.domain.entity.Planets
import retrofit2.Response
import retrofit2.http.GET

interface PlanetsService {
    @GET("/planets")
    suspend fun getPlanets(): Response<Planets>
}