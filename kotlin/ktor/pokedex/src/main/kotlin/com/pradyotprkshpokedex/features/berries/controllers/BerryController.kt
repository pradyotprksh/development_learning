package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import io.ktor.server.application.ApplicationCall

interface BerryController {
    suspend fun getBerriesByPagination(context: ApplicationCall, berriesResource: BerriesResource.Pagination)

    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAllBerries(context: ApplicationCall)

    suspend fun getBerryDetails(context: ApplicationCall, berriesResource: BerriesResource.Id)
}