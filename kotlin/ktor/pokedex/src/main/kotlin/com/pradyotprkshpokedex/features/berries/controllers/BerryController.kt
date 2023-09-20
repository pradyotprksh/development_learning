package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import io.ktor.server.application.ApplicationCall

interface BerryController {
    suspend fun getByPagination(context: ApplicationCall, resource: BerriesResource.Pagination)

    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall)

    suspend fun getDetails(context: ApplicationCall, resource: BerriesResource.Id)
}