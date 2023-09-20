package com.pradyotprkshpokedex.features.moves.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.domain.modal.LearnMethod
import com.pradyotprkshpokedex.features.moves.controllers.MoveLearnMethodsController
import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class MoveLearnMethodsControllerImplementation(
    private val moveService: MoveService,
    private val defaultController: DefaultController,
): MoveLearnMethodsController {
    override suspend fun getAll(context: ApplicationCall, resource: MovesResource.LearnMethod) {
        val allLearnMethods = moveService.getLearnMethodByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<LearnMethod>(allLearnMethods))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: MovesResource.LearnMethod.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, moveService.getLearnMethodDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: MovesResource.LearnMethod.Pagination) {
        if (resource.isValid) {
            val learnMethods =
                moveService.getLearnMethodByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<LearnMethod>(learnMethods))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    learnMethods
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