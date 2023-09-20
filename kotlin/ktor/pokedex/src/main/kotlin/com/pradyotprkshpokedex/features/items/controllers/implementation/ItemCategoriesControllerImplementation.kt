package com.pradyotprkshpokedex.features.items.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.ItemService
import com.pradyotprkshpokedex.domain.modal.Category
import com.pradyotprkshpokedex.features.items.controllers.ItemCategoriesController
import com.pradyotprkshpokedex.features.items.resource.ItemsResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class ItemCategoriesControllerImplementation(
    private val itemService: ItemService,
    private val defaultController: DefaultController,
): ItemCategoriesController {
    override suspend fun getAll(context: ApplicationCall, resource: ItemsResource.Category) {
        val allCategories = itemService.getCategoryByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Category>(allCategories))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: ItemsResource.Category.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, itemService.getCategoryDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: ItemsResource.Category.Pagination) {
        if (resource.isValid) {
            val categories =
                itemService.getCategoryByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Category>(categories))
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