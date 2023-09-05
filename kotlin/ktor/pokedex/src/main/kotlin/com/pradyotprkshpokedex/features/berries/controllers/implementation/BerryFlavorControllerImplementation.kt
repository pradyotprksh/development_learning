package com.pradyotprkshpokedex.features.berries.controllers.implementation

import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.features.berries.controllers.BerryFlavorController
import com.pradyotprkshpokedex.features.berries.resource.BerryResource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class BerryFlavorControllerImplementation(
    private val berryService: BerryService,
): BerryFlavorController {
    override suspend fun getAllBerryFlavor(context: ApplicationCall, flavor: BerryResource.BerryFlavor) {
        context.respond(status = HttpStatusCode.OK, "getAllBerryFlavor")
    }

    override suspend fun getBerryFlavorDetails(context: ApplicationCall, flavor: BerryResource.BerryFlavor.Id) {
        context.respond(status = HttpStatusCode.OK, "getBerryFlavorDetails")
    }

    override suspend fun getBerryFlavorByPagination(
        context: ApplicationCall,
        flavor: BerryResource.BerryFlavor.Pagination
    ) {
        context.respond(status = HttpStatusCode.OK, "getBerryFlavorByPagination")
    }

}