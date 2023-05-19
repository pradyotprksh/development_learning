package com.pradyotprakash.findingfalcone.domain.usecase

import com.pradyotprakash.findingfalcone.domain.repositories.PlanetsRepositories
import javax.inject.Inject

class PlanetsUseCase @Inject constructor(
    private val planetsRepositories: PlanetsRepositories,
) {
    suspend fun getPlanets() = planetsRepositories.getPlanets()
}