package com.pradyotprkshpokedex.features.evolution.controllers.implementation

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.EvolutionService
import com.pradyotprkshpokedex.domain.modal.EvolutionChain
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionChainController
import com.pradyotprkshpokedex.features.evolution.resource.EvolutionResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EvolutionChainControllerImplementation(
    private val evolutionService: EvolutionService
) : EvolutionChainController {
    override suspend fun getAllEvolutionChain(context: ApplicationCall, chains: EvolutionResource.Chains) {
        val allEvolutionChain = evolutionService.getEvolutionChainByPagination(offset = 0, limit = Int.MAX_VALUE)
        respondWithEvolutionChainDetails(context, allEvolutionChain)
    }

    override suspend fun getEvolutionChainDetails(context: ApplicationCall, chains: EvolutionResource.Chains.Id) {
        if (chains.isValid) {
            context.respond(status = HttpStatusCode.OK, evolutionService.getEvolutionChainDetails(id = chains.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getEvolutionChainByPagination(
        context: ApplicationCall,
        chains: EvolutionResource.Chains.Pagination
    ) {
        if (chains.isValid) {
            val evolutionChain =
                evolutionService.getEvolutionChainByPagination(offset = chains.offset, limit = chains.limit)
            if (chains.withDetails) {
                respondWithEvolutionChainDetails(context, evolutionChain)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    evolutionChain
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

    private suspend fun respondWithEvolutionChainDetails(context: ApplicationCall, evolutionChain: Pagination) {
        coroutineScope {
            val count = evolutionChain.results.size
            val channels = Channel<EvolutionChain>()
            evolutionChain.results.forEach { result ->
                result.url?.let { url ->
                    launch {
                        delay(1)
                        channels.send(
                            evolutionService.getEvolutionChainDetails(id = 0, path = url)
                        )
                    }
                }
            }
            val evolutionChainsDetails = mutableListOf<EvolutionChain>()
            repeat(count) {
                evolutionChainsDetails.add(channels.receive())
            }

            context.respond(status = HttpStatusCode.OK, evolutionChainsDetails)
        }
    }
}