package com.pradyotprkshpokedex.features.pokemon.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.PokemonService
import com.pradyotprkshpokedex.domain.modal.GrowthRate
import com.pradyotprkshpokedex.features.pokemon.controllers.GrowthRatesController
import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class GrowthRatesControllerImplementation(
    private val pokemonService: PokemonService,
    private val defaultController: DefaultController,
): GrowthRatesController {
    override suspend fun getAll(context: ApplicationCall, resource: PokemonResource.GrowthRate) {
        val all = pokemonService.getGrowthRateByPagination(offset = 0, limit = Int.MAX_VALUE)
        context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<GrowthRate>(all))
    }

    override suspend fun getDetails(context: ApplicationCall, resource: PokemonResource.GrowthRate.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, pokemonService.getGrowthRateDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: PokemonResource.GrowthRate.Pagination) {
        if (resource.isValid) {
            val details =
                pokemonService.getGrowthRateByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                context.respond(status = HttpStatusCode.OK, defaultController.respondWithDetails<GrowthRate>(details))
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    details
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