package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.features.berries.resource.BerryResource
import io.ktor.server.application.*

interface BerryFirmnessController {
    suspend fun getAllBerryFirmness(context: ApplicationCall, firmnesses: BerryResource.BerryFirmness)

    suspend fun getBerryFirmnessDetails(context: ApplicationCall, firmnesses: BerryResource.BerryFirmness.Id)

    suspend fun getBerryFirmnessByPagination(
        context: ApplicationCall,
        firmnesses: BerryResource.BerryFirmness.Pagination
    )
}