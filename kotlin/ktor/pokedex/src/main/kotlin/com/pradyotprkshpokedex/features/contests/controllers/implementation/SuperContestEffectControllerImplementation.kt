package com.pradyotprkshpokedex.features.contests.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.ContestService
import com.pradyotprkshpokedex.domain.modal.SuperContestEffect
import com.pradyotprkshpokedex.features.contests.controllers.SuperContestEffectController
import com.pradyotprkshpokedex.features.contests.resource.ContestResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class SuperContestEffectControllerImplementation(
    private val contestService: ContestService,
    private val defaultController: DefaultController,
) : SuperContestEffectController {
    override suspend fun getAll(context: ApplicationCall, resource: ContestResource.SupperContestEffect) {
        val allSuperContestEffects = contestService.getSuperContestEffectByPagination(offset = 0, limit = Int.MAX_VALUE)

            defaultController.respondWithDetails<SuperContestEffect>(context, allSuperContestEffects)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: ContestResource.SupperContestEffect.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, contestService.getSuperContestEffectDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(
        context: ApplicationCall,
        resource: ContestResource.SupperContestEffect.Pagination
    ) {
        if (resource.isValid) {
            val superContestEffects =
                contestService.getSuperContestEffectByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {

                    defaultController.respondWithDetails<SuperContestEffect>(context, superContestEffects)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    superContestEffects
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