package com.pradyotprkshpokedex.features.pokemon.controllers

import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import io.ktor.server.application.ApplicationCall

interface PokemonHabitatsController {
    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use pagination route to get results in paginated format.
     */
    suspend fun getAll(context: ApplicationCall, resource: PokemonResource.Habitat)

    suspend fun getDetails(context: ApplicationCall, resource: PokemonResource.Habitat.Id)

    suspend fun getByPagination(
        context: ApplicationCall,
        resource: PokemonResource.Habitat.Pagination
    )
}