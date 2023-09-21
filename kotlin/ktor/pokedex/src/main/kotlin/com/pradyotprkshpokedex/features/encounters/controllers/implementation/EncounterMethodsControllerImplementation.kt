package com.pradyotprkshpokedex.features.encounters.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.EncounterService
import com.pradyotprkshpokedex.domain.modal.Method
import com.pradyotprkshpokedex.features.encounters.controllers.EncounterMethodsController
import com.pradyotprkshpokedex.features.encounters.resource.EncountersResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class EncounterMethodsControllerImplementation(
    private val encounterService: EncounterService,
    private val defaultController: DefaultController,
) : EncounterMethodsController {
    override suspend fun getAll(context: ApplicationCall, resource: EncountersResource.Methods) {
        val allMethods = encounterService.getMethodByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Method>(context, allMethods)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: EncountersResource.Methods.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, encounterService.getMethodDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: EncountersResource.Methods.Pagination) {
        if (resource.isValid) {
            val methods =
                encounterService.getMethodByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<Method>(context, methods)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    methods
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