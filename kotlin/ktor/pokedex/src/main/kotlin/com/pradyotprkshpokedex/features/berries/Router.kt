package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.berries.resource.Berry
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.server.resources.*
import io.ktor.server.routing.*

/**
 * Berries are small fruits that can provide HP and status condition restoration, stat enhancement,
 * and even damage negation when eaten by Pokémon.
 */
fun Routing.berries(berriesController: BerriesController) {
    get<Berry> { berriesController.getAllBerries(this.context) }
    get<Berry.Id> { berriesController.getBerryDetails(this.context) }
    get<Berry.Pagination> { berriesController.getBerriesByPagination(this.context) }

    route(Paths.Berries.FIRMNESS) {
        get { }
        get(Paths.ID) { }
    }

    route(Paths.Berries.FLAVOR) {
        get { }
        get(Paths.ID) { }
    }
}