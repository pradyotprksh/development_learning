package com.pradyotprkshpokedex.features.moves.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.domain.modal.Target
import com.pradyotprkshpokedex.features.moves.controllers.MoveTargetsController
import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class MoveTargetsControllerImplementation(
    private val moveService: MoveService,
    private val defaultController: DefaultController,
) : MoveTargetsController {
    override suspend fun getAll(context: ApplicationCall, resource: MovesResource.Target) {
        val allTargets = moveService.getTargetByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Target>(allTargets))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: MovesResource.Target.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, moveService.getTargetDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: MovesResource.Target.Pagination) {
        if (resource.isValid) {
            val targets =
                moveService.getTargetByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Target>(targets))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    targets
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