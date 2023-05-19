package com.pradyotprakash.findingfalcone.domain.usecase

import com.pradyotprakash.findingfalcone.domain.repositories.VehiclesRepositories
import javax.inject.Inject

class VehiclesUseCase @Inject constructor(
    private val vehiclesRepositories: VehiclesRepositories,
) {
    suspend fun getVehicles() = vehiclesRepositories.getVehicles()
}