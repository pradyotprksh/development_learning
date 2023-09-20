package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import io.ktor.server.application.ApplicationCall

interface BerryFirmnessController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: BerriesResource.BerryFirmness)

    suspend fun getDetails(context: ApplicationCall, resource: BerriesResource.BerryFirmness.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: BerriesResource.BerryFirmness.Pagination
    )
}