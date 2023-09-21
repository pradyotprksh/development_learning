package com.pradyotprkshpokedex.features.games.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.GameService
import com.pradyotprkshpokedex.domain.modal.Version
import com.pradyotprkshpokedex.features.games.controllers.GameVersionController
import com.pradyotprkshpokedex.features.games.resource.GamesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class GameVersionControllerImplementation(
    private val gameService: GameService,
    private val defaultController: DefaultController,
) : GameVersionController {
    override suspend fun getAll(context: ApplicationCall, resource: GamesResource.Version) {
        val allVersions = gameService.getVersionByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Version>(context, allVersions)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: GamesResource.Version.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, gameService.getVersionDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: GamesResource.Version.Pagination) {
        if (resource.isValid) {
            val versions =
                gameService.getVersionByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<Version>(context, versions)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    versions
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