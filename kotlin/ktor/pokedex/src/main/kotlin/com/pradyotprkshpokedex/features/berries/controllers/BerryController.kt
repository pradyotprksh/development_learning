package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.features.berries.resource.BerryResource
import io.ktor.server.application.*

interface BerryController {
    suspend fun getBerriesByPagination(context: ApplicationCall, berryResource: BerryResource.Pagination)

    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use berry/pagination route to get results in paginated format.
     */
    suspend fun getAllBerries(context: ApplicationCall)

    suspend fun getBerryDetails(context: ApplicationCall, berryResource: BerryResource.Id)
}