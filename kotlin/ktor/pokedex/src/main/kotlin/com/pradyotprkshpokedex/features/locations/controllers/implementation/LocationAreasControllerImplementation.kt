package com.pradyotprkshpokedex.features.locations.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.LocationService
import com.pradyotprkshpokedex.domain.modal.Area
import com.pradyotprkshpokedex.features.locations.controllers.LocationAreasController
import com.pradyotprkshpokedex.features.locations.resource.LocationsResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class LocationAreasControllerImplementation(
    private val locationService: LocationService,
    private val defaultController: DefaultController,
) : LocationAreasController {
    override suspend fun getAll(context: ApplicationCall, resource: LocationsResource.Area) {
        val allAreas = locationService.getAreaByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Area>(context, allAreas)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: LocationsResource.Area.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, locationService.getAreaDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: LocationsResource.Area.Pagination) {
        if (resource.isValid) {
            val areas =
                locationService.getAreaByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<Area>(context, areas)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    areas
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