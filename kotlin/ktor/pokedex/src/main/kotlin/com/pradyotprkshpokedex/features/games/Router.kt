package com.pradyotprkshpokedex.features.games

import com.pradyotprkshpokedex.features.games.resource.GamesResource
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.games() {
    get<GamesResource.Generation> { }
    get<GamesResource.Generation.Id> { }
    get<GamesResource.Generation.Pagination> { }

    get<GamesResource.Pokedex> { }
    get<GamesResource.Pokedex.Id> { }
    get<GamesResource.Pokedex.Pagination> { }

    get<GamesResource.Version> { }
    get<GamesResource.Version.Id> { }
    get<GamesResource.Version.Pagination> { }

    get<GamesResource.VersionGroup> { }
    get<GamesResource.VersionGroup.Id> { }
    get<GamesResource.VersionGroup.Pagination> { }
}