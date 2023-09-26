package com.pradyotprkshpokedex.features.utility.controllers

import com.pradyotprkshpokedex.core.service.UtilityService
import com.pradyotprkshpokedex.domain.modal.PokemonImage
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class UtilityController(
    private val utilityService: UtilityService,
) {
    suspend fun getAllPokemonImages(context: ApplicationCall) {
        val images = utilityService.getAllPokemonImages()
        val urls = images.map {
            PokemonImage(
                id = it.name?.removeSuffix(".png")?.toIntOrNull() ?: Int.MAX_VALUE,
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${it.name}"
            )
        }

        val sortedUrls = urls
            .sortedBy { it.id }
            .subList(1, urls.size)

        context.respond(
            status = HttpStatusCode.OK,
            sortedUrls
        )
    }
}