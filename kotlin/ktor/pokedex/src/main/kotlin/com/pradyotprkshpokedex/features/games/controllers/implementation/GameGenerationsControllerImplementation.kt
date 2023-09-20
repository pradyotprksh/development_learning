package com.pradyotprkshpokedex.features.games.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.GameService
import com.pradyotprkshpokedex.domain.modal.Generation
import com.pradyotprkshpokedex.features.games.controllers.GameGenerationsController
import com.pradyotprkshpokedex.features.games.resource.GamesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class GameGenerationsControllerImplementation(
    private val gameService: GameService,
    private val defaultController: DefaultController,
) : GameGenerationsController {
    override suspend fun getAll(context: ApplicationCall, resource: GamesResource.Generation) {
        val allGenerations = gameService.getGenerationByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Generation>(allGenerations))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: GamesResource.Generation.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, gameService.getGenerationDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: GamesResource.Generation.Pagination) {
        if (resource.isValid) {
            val generations =
                gameService.getGenerationByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(
                    status = HttpStatusCode.OK,
                    defaultController.respondWithDetails<Generation>(generations)
                )
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    generations
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