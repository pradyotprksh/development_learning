package com.pradyotprkshpokedex.features.locations.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.LocationService
import com.pradyotprkshpokedex.domain.modal.PalPakArea
import com.pradyotprkshpokedex.features.locations.controllers.PalPakAreasController
import com.pradyotprkshpokedex.features.locations.resource.LocationsResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class PalPakAreasControllerImplementation(
    private val locationService: LocationService,
    private val defaultController: DefaultController,
): PalPakAreasController {
    override suspend fun getAll(context: ApplicationCall, resource: LocationsResource.PalPakArea) {
        val allPalPakAreas = locationService.getPalPakAreaByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<PalPakArea>(allPalPakAreas))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: LocationsResource.PalPakArea.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, locationService.getPalPakAreaDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: LocationsResource.PalPakArea.Pagination) {
        if (resource.isValid) {
            val palPakAreas =
                locationService.getPalPakAreaByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<PalPakArea>(palPakAreas))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    palPakAreas
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