package com.pradyotprkshpokedex.features.moves.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.domain.modal.BattleStyle
import com.pradyotprkshpokedex.features.moves.controllers.MoveBattleStylesController
import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class MoveBattleStylesControllerImplementation(
    private val moveService: MoveService,
    private val defaultController: DefaultController,
) : MoveBattleStylesController {
    override suspend fun getAll(context: ApplicationCall, resource: MovesResource.BattleStyle) {
        val allBattleStyles = moveService.getBattleStyleByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<BattleStyle>(allBattleStyles))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: MovesResource.BattleStyle.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, moveService.getBattleStyleDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: MovesResource.BattleStyle.Pagination) {
        if (resource.isValid) {
            val battleStyles =
                moveService.getBattleStyleByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(
                    status = HttpStatusCode.OK,
                    defaultController.respondWithDetails<BattleStyle>(battleStyles)
                )
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    battleStyles
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