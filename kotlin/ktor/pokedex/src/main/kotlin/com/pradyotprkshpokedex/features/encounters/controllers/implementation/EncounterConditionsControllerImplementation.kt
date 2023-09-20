package com.pradyotprkshpokedex.features.encounters.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.EncounterService
import com.pradyotprkshpokedex.domain.modal.Condition
import com.pradyotprkshpokedex.features.encounters.controllers.EncounterConditionsController
import com.pradyotprkshpokedex.features.encounters.resource.EncountersResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class EncounterConditionsControllerImplementation(
    private val encounterService: EncounterService,
    private val defaultController: DefaultController,
) : EncounterConditionsController {
    override suspend fun getAll(context: ApplicationCall, resource: EncountersResource.Condition) {
        val allCondition = encounterService.getConditionByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Condition>(allCondition))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: EncountersResource.Condition.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, encounterService.getConditionDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: EncountersResource.Condition.Pagination) {
        if (resource.isValid) {
            val conditions =
                encounterService.getConditionByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<Condition>(conditions))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    conditions
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