package com.pradyotprkshpokedex.features.games.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.GameService
import com.pradyotprkshpokedex.domain.modal.Pokedex
import com.pradyotprkshpokedex.features.games.controllers.GamePokedexesController
import com.pradyotprkshpokedex.features.games.resource.GamesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class GamePokedexesControllerImplementation(
    private val gameService: GameService,
    private val defaultController: DefaultController,
) : GamePokedexesController {
    override suspend fun getAll(context: ApplicationCall, resource: GamesResource.Pokedex) {
        val allPokedexes = gameService.getPokedexByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Pokedex>(context, allPokedexes)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: GamesResource.Pokedex.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, gameService.getPokedexDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: GamesResource.Pokedex.Pagination) {
        if (resource.isValid) {
            val pokedexes =
                gameService.getPokedexByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<Pokedex>(context, pokedexes)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    pokedexes
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