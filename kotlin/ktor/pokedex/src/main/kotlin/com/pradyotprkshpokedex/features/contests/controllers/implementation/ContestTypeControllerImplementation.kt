package com.pradyotprkshpokedex.features.contests.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.ContestService
import com.pradyotprkshpokedex.domain.modal.ContestType
import com.pradyotprkshpokedex.features.contests.controllers.ContestTypeController
import com.pradyotprkshpokedex.features.contests.resource.ContestResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class ContestTypeControllerImplementation(
    private val contestService: ContestService,
    private val defaultController: DefaultController,
) : ContestTypeController {
    override suspend fun getAll(context: ApplicationCall, resource: ContestResource.Type) {
        val allTypes = contestService.getTypeByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<ContestType>(context, allTypes)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: ContestResource.Type.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, contestService.getTypeDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: ContestResource.Type.Pagination) {
        if (resource.isValid) {
            val types =
                contestService.getTypeByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<ContestType>(context, types)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    types
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