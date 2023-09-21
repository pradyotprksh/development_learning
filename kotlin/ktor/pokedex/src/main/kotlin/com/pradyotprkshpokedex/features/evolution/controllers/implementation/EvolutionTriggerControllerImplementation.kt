package com.pradyotprkshpokedex.features.evolution.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.EvolutionService
import com.pradyotprkshpokedex.domain.modal.EvolutionTrigger
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionTriggerController
import com.pradyotprkshpokedex.features.evolution.resource.EvolutionResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class EvolutionTriggerControllerImplementation(
    private val evolutionService: EvolutionService, private val defaultController: DefaultController,
) : EvolutionTriggerController {
    override suspend fun getAllEvolutionTrigger(context: ApplicationCall, triggers: EvolutionResource.Triggers) {
        val allEvolutionTrigger = evolutionService.getEvolutionTriggerByPagination(offset = 0, limit = Int.MAX_VALUE)

        defaultController.respondWithDetails<EvolutionTrigger>(context, allEvolutionTrigger)
    }

    override suspend fun getEvolutionTriggerDetails(context: ApplicationCall, triggers: EvolutionResource.Triggers.Id) {
        if (triggers.isValid) {
            context.respond(status = HttpStatusCode.OK, evolutionService.getEvolutionTriggerDetails(id = triggers.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getEvolutionTriggerByPagination(
        context: ApplicationCall,
        triggers: EvolutionResource.Triggers.Pagination
    ) {
        if (triggers.isValid) {
            val evolutionTrigger =
                evolutionService.getEvolutionTriggerByPagination(offset = triggers.offset, limit = triggers.limit)
            if (triggers.withDetails) {

                defaultController.respondWithDetails<EvolutionTrigger>(context, evolutionTrigger)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    evolutionTrigger
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