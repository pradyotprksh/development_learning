package com.pradyotprkshpokedex.features.locations.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.LocationService
import com.pradyotprkshpokedex.domain.modal.Location
import com.pradyotprkshpokedex.domain.modal.Region
import com.pradyotprkshpokedex.features.locations.controllers.RegionsController
import com.pradyotprkshpokedex.features.locations.resource.LocationsResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class RegionsControllerImplementation(
    private val locationService: LocationService,
    private val defaultController: DefaultController,
) : RegionsController {
    override suspend fun getAll(context: ApplicationCall, resource: LocationsResource.Region) {
        val allRegions = locationService.getRegionEffectByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Location>(allRegions))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: LocationsResource.Region.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, locationService.getRegionEffectDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: LocationsResource.Region.Pagination) {
        if (resource.isValid) {
            val regions =
                locationService.getRegionEffectByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Region>(regions))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    regions
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