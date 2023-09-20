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
    override suspend fun getAll(context: ApplicationCall, resource: EvolutionResource.Chains) {
        val allEvolutionChain = evolutionService.getEvolutionChainByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<EvolutionChain>(allEvolutionChain))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: EvolutionResource.Chains.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, evolutionService.getEvolutionChainDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(
        context: ApplicationCall,
        resource: EvolutionResource.Chains.Pagination
    ) {
        if (resource.isValid) {
            val evolutionChain =
                evolutionService.getEvolutionChainByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<EvolutionChain>(evolutionChain))
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