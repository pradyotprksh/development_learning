package com.pradyotprkshpokedex.features.pokemon.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.PokemonService
import com.pradyotprkshpokedex.domain.modal.PokeathlonStat
import com.pradyotprkshpokedex.features.pokemon.controllers.PokeathlonStatController
import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class PokeathlonStatControllerImplementation(
    private val pokemonService: PokemonService,
    private val defaultController: DefaultController,
): PokeathlonStatController {
    override suspend fun getAll(context: ApplicationCall, resource: PokemonResource.PokeathlonStat) {
        val all = pokemonService.getPokeathlonStatByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<PokeathlonStat>(context, all)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: PokemonResource.PokeathlonStat.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, pokemonService.getPokeathlonStatDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(
        context: ApplicationCall,
        resource: PokemonResource.PokeathlonStat.Pagination
    ) {
        if (resource.isValid) {
            val details =
                pokemonService.getPokeathlonStatByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<PokeathlonStat>(context, details)
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