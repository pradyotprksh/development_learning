package com.pradyotprakash.findingfalcone.domain.usecase

import com.pradyotprakash.findingfalcone.core.response.FindingFalconeResponse
import com.pradyotprakash.findingfalcone.domain.entity.FindEntity
import com.pradyotprakash.findingfalcone.domain.repositories.FindRepositories
import com.pradyotprakash.findingfalcone.domain.repositories.TokenRepositories
import javax.inject.Inject

class FindUseCase @Inject constructor(
    private val tokenRepositories: TokenRepositories,
    private val findRepositories: FindRepositories,
) {
    suspend fun findFalcone(
        planetNames: List<String>,
        vehicleNames: List<String>,
    ): FindingFalconeResponse<FindEntity> {
        return when (val token = tokenRepositories.getToken()) {
            is FindingFalconeResponse.Error -> return token
            is FindingFalconeResponse.Success -> {
                findRepositories.findFalcone(
                    token = token.data.token,
                    planetNames = planetNames,
                    vehicleNames = vehicleNames,
                )
            }
        }
    }
}