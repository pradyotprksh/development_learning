package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.features.berries.resource.BerryResource
import io.ktor.server.application.*

interface BerryFlavorController {
    suspend fun getAllBerryFlavor(context: ApplicationCall, flavor: BerryResource.BerryFlavor)

    suspend fun getBerryFlavorDetails(context: ApplicationCall, flavor: BerryResource.BerryFlavor.Id)

    suspend fun getBerryFlavorByPagination(context: ApplicationCall, flavor: BerryResource.BerryFlavor.Pagination)
}