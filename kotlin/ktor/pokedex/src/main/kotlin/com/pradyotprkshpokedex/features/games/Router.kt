package com.pradyotprkshpokedex.features.games

import com.pradyotprkshpokedex.features.games.resource.GamesResource
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing

fun Routing.games() {
    get<GamesResource.Generation> { call.respond(status = HttpStatusCode.OK, "") }
    get<GamesResource.Generation.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<GamesResource.Generation.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<GamesResource.Pokedex> { call.respond(status = HttpStatusCode.OK, "") }
    get<GamesResource.Pokedex.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<GamesResource.Pokedex.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<GamesResource.Version> { call.respond(status = HttpStatusCode.OK, "") }
    get<GamesResource.Version.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<GamesResource.Version.Pagination> { call.respond(status = HttpStatusCode.OK, "") }

    get<GamesResource.VersionGroup> { call.respond(status = HttpStatusCode.OK, "") }
    get<GamesResource.VersionGroup.Id> { call.respond(status = HttpStatusCode.OK, "") }
    get<GamesResource.VersionGroup.Pagination> { call.respond(status = HttpStatusCode.OK, "") }
}