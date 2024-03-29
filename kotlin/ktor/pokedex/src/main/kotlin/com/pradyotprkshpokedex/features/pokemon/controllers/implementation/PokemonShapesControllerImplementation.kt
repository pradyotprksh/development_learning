package com.pradyotprkshpokedex.features.pokemon.controllers.implementation

import com.pradyotprkshpokedex.core.controller.DefaultController
import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.PokemonService
import com.pradyotprkshpokedex.domain.modal.Shape
import com.pradyotprkshpokedex.features.pokemon.controllers.PokemonShapesController
import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class PokemonShapesControllerImplementation(
    private val pokemonService: PokemonService,
    private val defaultController: DefaultController,
) : PokemonShapesController {
    override suspend fun getAll(context: ApplicationCall, resource: PokemonResource.Shape) {
        val all = pokemonService.getShapeByPagination(offset = 0, limit = Int.MAX_VALUE)
        defaultController.respondWithDetails<Shape>(context, all)
    }

    override suspend fun getDetails(context: ApplicationCall, resource: PokemonResource.Shape.Id) {
        if (resource.isValid) {
            context.respond(status = HttpStatusCode.OK, pokemonService.getShapeDetails(id = resource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getByPagination(context: ApplicationCall, resource: PokemonResource.Shape.Pagination) {
        if (resource.isValid) {
            val details =
                pokemonService.getShapeByPagination(offset = resource.offset, limit = resource.limit)
            if (resource.withDetails) {
                defaultController.respondWithDetails<Shape>(context, details)
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