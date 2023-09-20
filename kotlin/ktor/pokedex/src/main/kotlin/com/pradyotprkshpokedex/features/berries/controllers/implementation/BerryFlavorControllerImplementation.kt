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
    override suspend fun getAll(context: ApplicationCall, resource: BerriesResource.BerryFlavor) {
        val allBerryFlavor = berryService.getBerriesFlavorByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<BerryFlavor>(allBerryFlavor))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: BerriesResource.BerryFlavor.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, berryService.getBerryFlavorDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(
        context: ApplicationCall,
        resource: BerriesResource.BerryFlavor.Pagination
    ) {
        if (resource.isValid) {
            val berryFlavor =
                berryService.getBerriesFlavorByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(
                    status = HttpStatusCode.OK,
                    defaultController.respondWithDetails<BerryFlavor>(berryFlavor)
                )
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