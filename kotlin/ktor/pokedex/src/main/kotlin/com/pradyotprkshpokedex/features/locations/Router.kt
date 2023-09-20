package com.pradyotprkshpokedex.features.locations

import com.pradyotprkshpokedex.features.locations.controllers.LocationsController
import com.pradyotprkshpokedex.features.locations.resource.LocationsResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.locations(locationsController: LocationsController) {
    get<LocationsResource> { locationsController.getAll(this.context, it) }
    get<LocationsResource.Id> { locationsController.getDetails(this.context, it) }
    get<LocationsResource.Pagination> { locationsController.getByPagination(this.context, it) }

    get<LocationsResource.Area> { locationsController.getAll(this.context, it) }
    get<LocationsResource.Area.Id> { locationsController.getDetails(this.context, it) }
    get<LocationsResource.Area.Pagination> { locationsController.getByPagination(this.context, it) }

    get<LocationsResource.PalPakArea> { locationsController.getAll(this.context, it) }
    get<LocationsResource.PalPakArea.Id> { locationsController.getDetails(this.context, it) }
    get<LocationsResource.PalPakArea.Pagination> { locationsController.getByPagination(this.context, it) }

    get<LocationsResource.Region> { locationsController.getAll(this.context, it) }
    get<LocationsResource.Region.Id> { locationsController.getDetails(this.context, it) }
    get<LocationsResource.Region.Pagination> { locationsController.getByPagination(this.context, it) }
}