package com.pradyotprkshpokedex.features.items.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.ItemService
import com.pradyotprkshpokedex.domain.modal.Attribute
import com.pradyotprkshpokedex.features.items.controllers.ItemAttributesController
import com.pradyotprkshpokedex.features.items.resource.ItemsResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class ItemAttributesControllerImplementation(
    private val itemService: ItemService,
    private val defaultController: DefaultController,
) : ItemAttributesController {
    override suspend fun getAll(context: ApplicationCall, resource: ItemsResource.Attribute) {
        val allAttributes = itemService.getAttributeByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Attribute>(context, allAttributes)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: ItemsResource.Attribute.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, itemService.getAttributeDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: ItemsResource.Attribute.Pagination) {
        if (resource.isValid) {
            val attributes =
                itemService.getAttributeByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<Attribute>(context, attributes)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    attributes
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