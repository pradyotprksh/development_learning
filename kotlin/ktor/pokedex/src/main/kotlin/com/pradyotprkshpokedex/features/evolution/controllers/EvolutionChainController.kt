package com.pradyotprkshpokedex.features.evolution.controllers

import com.pradyotprkshpokedex.features.evolution.resource.EvolutionResource
import io.ktor.server.application.ApplicationCall

interface EvolutionChainController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: EvolutionResource.Chains)

    suspend fun getDetails(context: ApplicationCall, resource: EvolutionResource.Chains.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: EvolutionResource.Chains.Pagination
    )
}