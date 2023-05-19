package com.pradyotprakash.findingfalcone.domain.repositories

import com.pradyotprakash.findingfalcone.core.response.parseResponse
import com.pradyotprakash.findingfalcone.data.services.VehiclesService

class VehiclesRepositories(
    private val vehiclesService: VehiclesService,
) {
    suspend fun getVehicles() = vehiclesService.getVehicles().parseResponse()
}