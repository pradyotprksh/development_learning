package com.pradyotprkshpokedex.features.contests.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.ContestService
import com.pradyotprkshpokedex.domain.modal.Berry
import com.pradyotprkshpokedex.domain.modal.ContestEffect
import com.pradyotprkshpokedex.features.contests.controllers.ContestEffectController
import com.pradyotprkshpokedex.features.contests.resource.ContestResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class ContestEffectControllerImplementation(
    private val contestService: ContestService,
    private val defaultController: DefaultController,
) : ContestEffectController {
    override suspend fun getAll(context: ApplicationCall, resource: ContestResource.Effect) {
        val allEffects = contestService.getEffectByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Berry>(context, allEffects)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: ContestResource.Effect.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, contestService.getEffectDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: ContestResource.Effect.Pagination) {
        if (resource.isValid) {
            val effects =
                contestService.getEffectByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<ContestEffect>(context, effects)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    effects
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