package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import io.ktor.server.application.*

interface BerryFirmnessController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAllBerryFirmness(context: ApplicationCall, firmnesses: BerriesResource.BerryFirmness)

    suspend fun getBerryFirmnessDetails(context: ApplicationCall, firmnesses: BerriesResource.BerryFirmness.Id)

    suspend fun getBerryFirmnessByPagination(
        context: ApplicationCall,
        firmnesses: BerriesResource.BerryFirmness.Pagination
    )
}