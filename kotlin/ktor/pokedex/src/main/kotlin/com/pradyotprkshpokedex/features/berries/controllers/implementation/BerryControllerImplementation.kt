package com.pradyotprkshpokedex.features.berries.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.Berry
import com.pradyotprkshpokedex.features.berries.controllers.BerryController
import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class BerryControllerImplementation(
    private val berryService: BerryService,
    private val defaultController: DefaultController,
) : BerryController {
    override suspend fun getBerriesByPagination(context: ApplicationCall, berriesResource: BerriesResource.Pagination) {
        if (berriesResource.isValid) {
            val berries =
                berryService.getBerriesByPagination(offset = berriesResource.offset, limit = berriesResource.limit)
            if (berriesResource.withDetails) {
                defaultController.respondWithDetails<Berry>(context, berries)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    berries
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

    override suspend fun getAllBerries(context: ApplicationCall) {
        val allBerries = berryService.getBerriesByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Berry>(context, allBerries)
    }

    override suspend fun getBerryDetails(context: ApplicationCall, berriesResource: BerriesResource.Id) {
        if (berriesResource.isValid) {
            context.respond(status = HttpStatusCode.OK, berryService.getBerryDetails(id = berriesResource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }
}