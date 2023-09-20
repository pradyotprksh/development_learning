package com.pradyotprkshpokedex.features.encounters.controllers

import com.pradyotprkshpokedex.features.encounters.resource.EncountersResource
import io.ktor.server.application.ApplicationCall

interface EncounterMethodsController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: EncountersResource.Methods)

    suspend fun getDetails(context: ApplicationCall, resource: EncountersResource.Methods.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: EncountersResource.Methods.Pagination
    )
}