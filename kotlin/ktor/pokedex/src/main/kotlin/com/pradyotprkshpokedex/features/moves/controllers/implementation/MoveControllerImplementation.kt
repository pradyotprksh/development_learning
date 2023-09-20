package com.pradyotprkshpokedex.features.moves.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.domain.modal.Move
import com.pradyotprkshpokedex.features.moves.controllers.MoveController
import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class MoveControllerImplementation(
    private val moveService: MoveService,
    private val defaultController: DefaultController,
): MoveController {
    override suspend fun getAll(context: ApplicationCall, resource: MovesResource) {
        val allMoves = moveService.getMoveByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Move>(allMoves))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: MovesResource.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, moveService.getMoveDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: MovesResource.Pagination) {
        if (resource.isValid) {
            val moves =
                moveService.getMoveByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Move>(moves))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    moves
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