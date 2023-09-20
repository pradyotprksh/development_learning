package com.pradyotprkshpokedex.features.games

import com.pradyotprkshpokedex.features.games.controllers.GamesController
import com.pradyotprkshpokedex.features.games.resource.GamesResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.games(gamesController: GamesController) {
    get<GamesResource.Generation> { gamesController.getAll(this.context, it) }
    get<GamesResource.Generation.Id> { gamesController.getDetails(this.context, it) }
    get<GamesResource.Generation.Pagination> { gamesController.getByPagination(this.context, it) }

    get<GamesResource.Pokedex> { gamesController.getAll(this.context, it) }
    get<GamesResource.Pokedex.Id> { gamesController.getDetails(this.context, it) }
    get<GamesResource.Pokedex.Pagination> { gamesController.getByPagination(this.context, it) }

    get<GamesResource.Version> { gamesController.getAll(this.context, it) }
    get<GamesResource.Version.Id> { gamesController.getDetails(this.context, it) }
    get<GamesResource.Version.Pagination> { gamesController.getByPagination(this.context, it) }

    get<GamesResource.VersionGroup> { gamesController.getAll(this.context, it) }
    get<GamesResource.VersionGroup.Id> { gamesController.getDetails(this.context, it) }
    get<GamesResource.VersionGroup.Pagination> { gamesController.getByPagination(this.context, it) }
}