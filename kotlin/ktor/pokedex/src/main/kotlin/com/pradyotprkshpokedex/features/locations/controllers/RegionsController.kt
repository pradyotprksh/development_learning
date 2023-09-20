package com.pradyotprkshpokedex.features.locations.controllers

import com.pradyotprkshpokedex.features.locations.resource.LocationsResource
import io.ktor.server.application.ApplicationCall

interface RegionsController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: LocationsResource.Region)

    suspend fun getDetails(context: ApplicationCall, resource: LocationsResource.Region.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: LocationsResource.Region.Pagination
    )
}