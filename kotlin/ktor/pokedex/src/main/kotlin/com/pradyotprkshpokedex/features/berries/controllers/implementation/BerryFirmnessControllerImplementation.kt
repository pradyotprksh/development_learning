package com.pradyotprkshpokedex.features.berries.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.BerryFirmness
import com.pradyotprkshpokedex.features.berries.controllers.BerryFirmnessController
import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class BerryFirmnessControllerImplementation(
    private val berryService: BerryService, private val defaultController: DefaultController,
) : BerryFirmnessController {
    override suspend fun getAll(context: ApplicationCall, resource: BerriesResource.BerryFirmness) {
        val allBerryFirmnesses = berryService.getBerriesFirmnessByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<BerryFirmness>(allBerryFirmnesses))
    }

    override suspend fun getDetails(
        context: ApplicationCall,
        resource: BerriesResource.BerryFirmness.Id
    ) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, berryService.getBerryFirmnessDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(
        context: ApplicationCall,
        resource: BerriesResource.BerryFirmness.Pagination
    ) {
        if (resource.isValid) {
            val berryFirmnesses =
                berryService.getBerriesFirmnessByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<BerryFirmness>(berryFirmnesses))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    berryFirmnesses
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