package com.pradyotprkshpokedex.features.utility

import com.pradyotprkshpokedex.features.utility.controllers.UtilityController
import com.pradyotprkshpokedex.features.utility.resource.UtilityResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.utility(utilityController: UtilityController) {
    get<UtilityResource.AllPokemonImages> { utilityController.getAllPokemonImages(this.context) }
}