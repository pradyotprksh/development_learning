package com.pradyotprakash.findingfalcone.domain.repositories

import com.pradyotprakash.findingfalcone.core.response.parseResponse
import com.pradyotprakash.findingfalcone.data.services.PlanetsService

class PlanetsRepositories(
    private val planetsService: PlanetsService,
) {
    suspend fun getPlanets() = planetsService.getPlanets().parseResponse()
}