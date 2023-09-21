package com.pradyotprkshpokedex.features.encounters.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.EncounterService
import com.pradyotprkshpokedex.domain.modal.ConditionValue
import com.pradyotprkshpokedex.features.encounters.controllers.EncounterConditionValuesController
import com.pradyotprkshpokedex.features.encounters.resource.EncountersResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class EncounterConditionValuesControllerImplementation(
    private val encounterService: EncounterService,
    private val defaultController: DefaultController,
) : EncounterConditionValuesController {
    override suspend fun getAll(context: ApplicationCall, resource: EncountersResource.ConditionValue) {
        val allConditionValues = encounterService.getConditionValueByPagination(offset = 0, limit = Int.MAX_VALUE)

            defaultController.respondWithDetails<ConditionValue>(context, allConditionValues)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: EncountersResource.ConditionValue.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, encounterService.getConditionValueDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(
        context: ApplicationCall,
        resource: EncountersResource.ConditionValue.Pagination
    ) {
        if (resource.isValid) {
            val conditionValues =
                encounterService.getConditionValueByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {

                    defaultController.respondWithDetails<ConditionValue>(context, conditionValues)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    conditionValues
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