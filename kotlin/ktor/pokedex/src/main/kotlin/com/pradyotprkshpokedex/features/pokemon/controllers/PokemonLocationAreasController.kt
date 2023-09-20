package com.pradyotprkshpokedex.features.pokemon.controllers

import com.pradyotprkshpokedex.features.pokemon.resource.PokemonResource
import io.ktor.server.application.ApplicationCall

interface PokemonLocationAreasController {
    suspend fun getDetails(context: ApplicationCall, resource: PokemonResource.Encounters.Id)
}