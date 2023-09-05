package com.pradyotprkshpokedex.features.berries.controllers.implementation

import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.features.berries.controllers.BerryFirmnessController
import com.pradyotprkshpokedex.features.berries.resource.BerryResource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class BerryFirmnessControllerImplementation(
    private val berryService: BerryService,
): BerryFirmnessController {
    override suspend fun getAllBerryFirmness(context: ApplicationCall, firmnesses: BerryResource.BerryFirmness) {
        context.respond(status = HttpStatusCode.OK, "getAllBerryFirmness")
    }

    override suspend fun getBerryFirmnessDetails(context: ApplicationCall, firmnesses: BerryResource.BerryFirmness.Id) {
        context.respond(status = HttpStatusCode.OK, "getBerryFirmnessDetails")
    }

    override suspend fun getBerryFirmnessByPagination(
        context: ApplicationCall,
        firmnesses: BerryResource.BerryFirmness.Pagination
    ) {
        context.respond(status = HttpStatusCode.OK, "getBerryFirmnessByPagination")
    }
}