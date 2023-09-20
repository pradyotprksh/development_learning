package com.pradyotprkshpokedex.features.games.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.GameService
import com.pradyotprkshpokedex.domain.modal.VersionGroup
import com.pradyotprkshpokedex.features.games.controllers.GameVersionGroupController
import com.pradyotprkshpokedex.features.games.resource.GamesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class GameVersionGroupControllerImplementation(
    private val gameService: GameService,
    private val defaultController: DefaultController,
): GameVersionGroupController {
    override suspend fun getAll(context: ApplicationCall, resource: GamesResource.VersionGroup) {
        val allVersionGroups = gameService.getVersionByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<VersionGroup>(allVersionGroups))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: GamesResource.VersionGroup.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, gameService.getVersionGroupDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: GamesResource.VersionGroup.Pagination) {
        if (resource.isValid) {
            val versionGroups =
                gameService.getVersionGroupByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<VersionGroup>(versionGroups))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    versionGroups
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