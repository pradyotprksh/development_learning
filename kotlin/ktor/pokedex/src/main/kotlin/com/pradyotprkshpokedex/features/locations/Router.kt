package com.pradyotprkshpokedex.features.locations

import com.pradyotprkshpokedex.features.locations.resource.LocationsResource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.locations() {
    get<LocationsResource> { call.respond(status = HttpStatusCode.OK, "") }
    get<LocationsResource.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<LocationsResource.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<LocationsResource.Area> { call.respond(status = HttpStatusCode.OK, "") }
    get<LocationsResource.Area.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<LocationsResource.Area.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<LocationsResource.PalPakArea> { call.respond(status = HttpStatusCode.OK, "") }
    get<LocationsResource.PalPakArea.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<LocationsResource.PalPakArea.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<LocationsResource.Region> { call.respond(status = HttpStatusCode.OK, "") }
    get<LocationsResource.Region.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<LocationsResource.Region.Pagination> { call.respond(status = HttpStatusCode.OK, "") }
}