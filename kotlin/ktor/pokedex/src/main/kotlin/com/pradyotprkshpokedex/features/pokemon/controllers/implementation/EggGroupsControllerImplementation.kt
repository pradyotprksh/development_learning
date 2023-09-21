package com.pradyotprkshpokedex.features.pokemon.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.PokemonService
import com.pradyotprkshpokedex.domain.modal.EggGroup
import com.pradyotprkshpokedex.features.pokemon.controllers.EggGroupsController
import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class EggGroupsControllerImplementation(
    private val pokemonService: PokemonService,
    private val defaultController: DefaultController,
) : EggGroupsController {
    override suspend fun getAll(context: ApplicationCall, resource: PokemonResource.EggGroup) {
        val all = pokemonService.getEggGroupByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<EggGroup>(context, all)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: PokemonResource.EggGroup.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, pokemonService.getEggGroupDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: PokemonResource.EggGroup.Pagination) {
        if (resource.isValid) {
            val details =
                pokemonService.getEggGroupByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<EggGroup>(context, details)
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