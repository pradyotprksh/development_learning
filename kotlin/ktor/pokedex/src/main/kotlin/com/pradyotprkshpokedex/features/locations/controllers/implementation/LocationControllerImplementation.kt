package com.pradyotprkshpokedex.features.locations.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.LocationService
import com.pradyotprkshpokedex.domain.modal.Location
import com.pradyotprkshpokedex.features.locations.controllers.LocationController
import com.pradyotprkshpokedex.features.locations.resource.LocationsResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class LocationControllerImplementation(
    private val locationService: LocationService,
    private val defaultController: DefaultController,
) : LocationController {
    override suspend fun getAll(context: ApplicationCall, resource: LocationsResource) {
        val allLocations = locationService.getLocationByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Location>(context, allLocations)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: LocationsResource.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, locationService.getLocationDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: LocationsResource.Pagination) {
        if (resource.isValid) {
            val locations =
                locationService.getAreaByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<Location>(context, locations)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    locations
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