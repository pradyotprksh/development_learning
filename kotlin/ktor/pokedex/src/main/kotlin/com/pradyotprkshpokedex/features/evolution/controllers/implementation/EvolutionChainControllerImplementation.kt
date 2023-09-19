package com.pradyotprkshpokedex.features.evolution.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.EvolutionService
import com.pradyotprkshpokedex.domain.modal.EvolutionChain
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionChainController
import com.pradyotprkshpokedex.features.evolution.resource.EvolutionResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class EvolutionChainControllerImplementation(
    private val evolutionService: EvolutionService, private val defaultController: DefaultController,
) : EvolutionChainController {
    override suspend fun getAllEvolutionChain(context: ApplicationCall, chains: EvolutionResource.Chains) {
        val allEvolutionChain = evolutionService.getEvolutionChainByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<EvolutionChain>(context, allEvolutionChain)
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
                defaultController.respondWithDetails<EvolutionChain>(context, evolutionChain)
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
}