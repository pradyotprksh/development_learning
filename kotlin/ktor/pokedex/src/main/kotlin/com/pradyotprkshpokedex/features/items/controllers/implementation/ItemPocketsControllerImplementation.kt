package com.pradyotprkshpokedex.features.items.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.ItemService
import com.pradyotprkshpokedex.domain.modal.Pocket
import com.pradyotprkshpokedex.features.items.controllers.ItemPocketsController
import com.pradyotprkshpokedex.features.items.resource.ItemsResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class ItemPocketsControllerImplementation(
    private val itemService: ItemService,
    private val defaultController: DefaultController,
): ItemPocketsController {
    override suspend fun getAll(context: ApplicationCall, resource: ItemsResource.Pockets) {
        val allPockets = itemService.getPocketByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Pocket>(allPockets))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: ItemsResource.Pockets.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, itemService.getPocketDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: ItemsResource.Pockets.Pagination) {
        if (resource.isValid) {
            val pockets =
                itemService.getPocketByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Pocket>(pockets))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    pockets
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