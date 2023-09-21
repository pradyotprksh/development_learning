package com.pradyotprkshpokedex.features.moves.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.domain.modal.Ailment
import com.pradyotprkshpokedex.features.moves.controllers.MoveAilmentsController
import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class MoveAilmentsControllerImplementation(
    private val moveService: MoveService,
    private val defaultController: DefaultController,
) : MoveAilmentsController {
    override suspend fun getAll(context: ApplicationCall, resource: MovesResource.Ailment) {
        val allAilments = moveService.getAilmentByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Ailment>(context, allAilments)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: MovesResource.Ailment.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, moveService.getAilmentDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: MovesResource.Ailment.Pagination) {
        if (resource.isValid) {
            val ailments =
                moveService.getAilmentByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<Ailment>(context, ailments)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    ailments
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