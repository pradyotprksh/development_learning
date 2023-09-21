package com.pradyotprkshpokedex.features.pokemon.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.PokemonService
import com.pradyotprkshpokedex.domain.modal.Stat
import com.pradyotprkshpokedex.features.pokemon.controllers.StatsController
import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class StatsControllerImplementation(
    private val pokemonService: PokemonService,
    private val defaultController: DefaultController,
): StatsController {
    override suspend fun getAll(context: ApplicationCall, resource: PokemonResource.Stat) {
        val all = pokemonService.getStatByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Stat>(context, all)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: PokemonResource.Stat.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, pokemonService.getStatDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: PokemonResource.Stat.Pagination) {
        if (resource.isValid) {
            val details =
                pokemonService.getStatByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<Stat>(context, details)
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