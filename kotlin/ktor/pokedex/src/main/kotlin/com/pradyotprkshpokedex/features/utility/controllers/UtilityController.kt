package com.pradyotprkshpokedex.features.utility.controllers

import com.pradyotprkshpokedex.core.service.UtilityService
import com.pradyotprkshpokedex.domain.modal.NameUrl
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class UtilityController(
    private val utilityService: UtilityService,
) {
    suspend fun getAllPokemonImages(context: ApplicationCall) {
        val images = utilityService.getAllPokemonImages()
        val urls = images.map {
            NameUrl(
                name = it.name,
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${it.name}"
            )
        }

        context.respond(
            status = HttpStatusCode.OK,
            urls
        )
    }
}