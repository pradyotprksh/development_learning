package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.berries.resource.AllBerries
import com.pradyotprkshpokedex.features.berries.resource.BerriesPagination
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get

/**
 * Berries are small fruits that can provide HP and status condition restoration, stat enhancement,
 * and even damage negation when eaten by Pokémon.
 */
fun Routing.berries(berriesController: BerriesController) {
    route(Paths.Berries.BERRY) {
        get<BerriesPagination> { berriesController.getBerriesByPagination(this.context) }
        get<AllBerries> { berriesController.getAllBerries(this.context) }
        get(Paths.ID) { }
    }

    route(Paths.Berries.FIRMNESS) {
        get { }
        get(Paths.ID) { }
    }

    route(Paths.Berries.FLAVOR) {
        get { }
        get(Paths.ID) { }
    }
}