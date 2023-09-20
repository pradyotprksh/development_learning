package com.pradyotprkshpokedex.features.contests.controllers

import com.pradyotprkshpokedex.features.contests.resource.ContestResource
import io.ktor.server.application.ApplicationCall

interface ContestTypeController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: ContestResource.Type)

    suspend fun getDetails(context: ApplicationCall, resource: ContestResource.Type.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: ContestResource.Type.Pagination
    )
}