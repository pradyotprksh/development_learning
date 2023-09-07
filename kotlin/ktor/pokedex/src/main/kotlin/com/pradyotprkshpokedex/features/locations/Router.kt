package com.pradyotprkshpokedex.features.locations

import com.pradyotprkshpokedex.features.locations.resource.LocationsResource
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.locations() {
    get<LocationsResource> { }
    get<LocationsResource.Id> { }
    get<LocationsResource.Pagination> { }

    get<LocationsResource.Area> { }
    get<LocationsResource.Area.Id> { }
    get<LocationsResource.Area.Pagination> { }

    get<LocationsResource.PalPakArea> { }
    get<LocationsResource.PalPakArea.Id> { }
    get<LocationsResource.PalPakArea.Pagination> { }

    get<LocationsResource.Region> { }
    get<LocationsResource.Region.Id> { }
    get<LocationsResource.Region.Pagination> { }
}