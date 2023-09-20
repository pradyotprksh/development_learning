package com.pradyotprkshpokedex.features.encounters.controllers

import com.pradyotprkshpokedex.features.encounters.resource.EncountersResource
import io.ktor.server.application.ApplicationCall

interface EncounterConditionsController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: EncountersResource.Condition)

    suspend fun getDetails(context: ApplicationCall, resource: EncountersResource.Condition.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: EncountersResource.Condition.Pagination
    )
}