package com.pradyotprkshpokedex.features.evolution.controllers

import com.pradyotprkshpokedex.features.evolution.resource.EvolutionResource
import io.ktor.server.application.ApplicationCall

interface EvolutionTriggerController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAllEvolutionTrigger(context: ApplicationCall, triggers: EvolutionResource.Triggers)

    suspend fun getEvolutionTriggerDetails(context: ApplicationCall, triggers: EvolutionResource.Triggers.Id)

    suspend fun getEvolutionTriggerByPagination(
        context: ApplicationCall,
        triggers: EvolutionResource.Triggers.Pagination
    )
}