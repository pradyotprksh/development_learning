package com.pradyotprakash.findingfalcone.domain.repositories

import com.pradyotprakash.findingfalcone.core.response.parseResponse
import com.pradyotprakash.findingfalcone.data.services.FindService
import com.pradyotprakash.findingfalcone.domain.entity.FindEntityRequestBody

class FindRepositories(
    private val findService: FindService,
) {
    suspend fun findFalcone(
        token: String,
        planetNames: List<String>,
        vehicleNames: List<String>,
    ) = findService.findFalcone(
        findEntityRequestBody = FindEntityRequestBody(
            token = token,
            planet_names = planetNames,
            vehicle_names = vehicleNames,
        )
    ).parseResponse()
}