package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import io.ktor.server.application.*

interface BerryFlavorController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAllBerryFlavor(context: ApplicationCall, flavor: BerriesResource.BerryFlavor)

    suspend fun getBerryFlavorDetails(context: ApplicationCall, flavor: BerriesResource.BerryFlavor.Id)

    suspend fun getBerryFlavorByPagination(context: ApplicationCall, flavor: BerriesResource.BerryFlavor.Pagination)
}