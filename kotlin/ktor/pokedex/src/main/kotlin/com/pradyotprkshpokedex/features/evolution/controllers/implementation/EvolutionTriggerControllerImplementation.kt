package com.pradyotprkshpokedex.features.evolution.controllers.implementation

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.EvolutionService
import com.pradyotprkshpokedex.domain.modal.EvolutionTrigger
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionTriggerController
import com.pradyotprkshpokedex.features.evolution.resource.EvolutionResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EvolutionTriggerControllerImplementation(
    private val evolutionService: EvolutionService
) : EvolutionTriggerController {
    override suspend fun getAllEvolutionTrigger(context: ApplicationCall, triggers: EvolutionResource.Triggers) {
        val allEvolutionTrigger = evolutionService.getEvolutionTriggerByPagination(offset = 0, limit = Int.MAX_VALUE)
        respondWithEvolutionTriggerDetails(context, allEvolutionTrigger)
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
                respondWithEvolutionTriggerDetails(context, evolutionTrigger)
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

    private suspend fun respondWithEvolutionTriggerDetails(
        context: ApplicationCall,
        evolutionTrigger: Pagination
    ) {
        coroutineScope {
            val count = evolutionTrigger.results.size
            val channels = Channel<EvolutionTrigger>()
            evolutionTrigger.results.forEach { result ->
                result.url?.let { url ->
                    launch {
                        delay(1)
                        channels.send(
                            evolutionService.getEvolutionTriggerDetails(id = 0, path = url)
                        )
                    }
                }
            }
            val evolutionTriggersDetails = mutableListOf<EvolutionTrigger>()
            repeat(count) {
                evolutionTriggersDetails.add(channels.receive())
            }

            context.respond(status = HttpStatusCode.OK, evolutionTriggersDetails)
        }
    }
}