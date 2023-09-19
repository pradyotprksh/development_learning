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
    override suspend fun getAllBerryFirmness(context: ApplicationCall, firmnesses: BerriesResource.BerryFirmness) {
        val allBerryFirmnesses = berryService.getBerriesFirmnessByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<BerryFirmness>(context, allBerryFirmnesses)
    }

    override suspend fun getBerryFirmnessDetails(
        context: ApplicationCall,
        firmnesses: BerriesResource.BerryFirmness.Id
    ) {
        if (firmnesses.isValid) {
            context.respond(status = HttpStatusCode.OK, berryService.getBerryFirmnessDetails(id = firmnesses.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getBerryFirmnessByPagination(
        context: ApplicationCall,
        firmnesses: BerriesResource.BerryFirmness.Pagination
    ) {
        if (firmnesses.isValid) {
            val berryFirmnesses =
                berryService.getBerriesFirmnessByPagination(offset = firmnesses.offset, limit = firmnesses.limit)
            if (firmnesses.withDetails) {
                defaultController.respondWithDetails<BerryFirmness>(context, berryFirmnesses)
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