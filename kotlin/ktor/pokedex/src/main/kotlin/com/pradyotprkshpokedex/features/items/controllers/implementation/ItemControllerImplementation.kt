package com.pradyotprkshpokedex.features.items.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.ItemService
import com.pradyotprkshpokedex.domain.modal.Item
import com.pradyotprkshpokedex.features.items.controllers.ItemController
import com.pradyotprkshpokedex.features.items.resource.ItemsResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class ItemControllerImplementation(
    private val itemService: ItemService,
    private val defaultController: DefaultController,
): ItemController {
    override suspend fun getAll(context: ApplicationCall, resource: ItemsResource) {
        val allItems = itemService.getItemByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Item>(allItems))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: ItemsResource.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, itemService.getItemDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: ItemsResource.Pagination) {
        if (resource.isValid) {
            val items =
                itemService.getItemByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Item>(items))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    items
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