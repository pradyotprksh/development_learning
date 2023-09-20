package com.pradyotprkshpokedex.features.moves.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.MoveService
import com.pradyotprkshpokedex.domain.modal.MoveCategory
import com.pradyotprkshpokedex.features.moves.controllers.MoveCategoriesController
import com.pradyotprkshpokedex.features.moves.resource.MovesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class MoveCategoriesControllerImplementation(
    private val moveService: MoveService,
    private val defaultController: DefaultController,
): MoveCategoriesController {
    override suspend fun getAll(context: ApplicationCall, resource: MovesResource.Category) {
        val allCategories = moveService.getCategoryByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<MoveCategory>(allCategories))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: MovesResource.Category.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, moveService.getCategoryDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: MovesResource.Category.Pagination) {
        if (resource.isValid) {
            val categories =
                moveService.getCategoryByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<MoveCategory>(categories))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    categories
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