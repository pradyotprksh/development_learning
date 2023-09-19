package com.pradyotprkshpokedex.features.berries.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.BerryFlavor
import com.pradyotprkshpokedex.features.berries.controllers.BerryFlavorController
import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class BerryFlavorControllerImplementation(
    private val berryService: BerryService, private val defaultController: DefaultController,
) : BerryFlavorController {
    override suspend fun getAllBerryFlavor(context: ApplicationCall, flavor: BerriesResource.BerryFlavor) {
        val allBerryFlavor = berryService.getBerriesFlavorByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<BerryFlavor>(context, allBerryFlavor)
    }

    override suspend fun getBerryFlavorDetails(context: ApplicationCall, flavor: BerriesResource.BerryFlavor.Id) {
        if (flavor.isValid) {
            context.respond(status = HttpStatusCode.OK, berryService.getBerryFlavorDetails(id = flavor.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getBerryFlavorByPagination(
        context: ApplicationCall,
        flavor: BerriesResource.BerryFlavor.Pagination
    ) {
        if (flavor.isValid) {
            val berryFlavor =
                berryService.getBerriesFlavorByPagination(offset = flavor.offset, limit = flavor.limit)
            if (flavor.withDetails) {
                defaultController.respondWithDetails<BerryFlavor>(context, berryFlavor)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    berryFlavor
                )
            }
        } else {
            throw ParametersInvalidException(
                invalidParameters = listOf(
                    Paths.Parameters.OFFSET,
                    Paths.Parameters.LIMIT
                )
            )
        }
    }
}