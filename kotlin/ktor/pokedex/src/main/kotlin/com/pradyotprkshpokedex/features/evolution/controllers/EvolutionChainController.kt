package com.pradyotprkshpokedex.features.evolution.controllers

import com.pradyotprkshpokedex.features.evolution.resource.EvolutionResource
import io.ktor.server.application.*

interface EvolutionChainController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAllEvolutionChain(context: ApplicationCall, chains: EvolutionResource.Chains)

    suspend fun getEvolutionChainDetails(context: ApplicationCall, chains: EvolutionResource.Chains.Id)

    suspend fun getEvolutionChainByPagination(
        context: ApplicationCall,
        chains: EvolutionResource.Chains.Pagination
    )
}