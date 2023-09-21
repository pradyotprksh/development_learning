package com.pradyotprkshpokedex.features.items.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.ItemService
import com.pradyotprkshpokedex.domain.modal.FilingEffect
import com.pradyotprkshpokedex.features.items.controllers.ItemFilingEffectsController
import com.pradyotprkshpokedex.features.items.resource.ItemsResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class ItemFilingEffectsControllerImplementation(
    private val itemService: ItemService,
    private val defaultController: DefaultController,
) : ItemFilingEffectsController {
    override suspend fun getAll(context: ApplicationCall, resource: ItemsResource.FilingEffect) {
        val allFilingEffects = itemService.getFilingEffectByPagination(offset = 0, limit = Int.MAX_VALUE)

        defaultController.respondWithDetails<FilingEffect>(context, allFilingEffects)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: ItemsResource.FilingEffect.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, itemService.getFilingEffectDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: ItemsResource.FilingEffect.Pagination) {
        if (resource.isValid) {
            val filingEffects =
                itemService.getFilingEffectByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {

                defaultController.respondWithDetails<FilingEffect>(context, filingEffects)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    filingEffects
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