package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.server.routing.*

fun Routing.berries(berriesController: BerriesController) {
    route(Paths.Berries.BERRY) {
        get(Paths.Berries.BERRY_PAGINATION) {
            berriesController.getBerriesByPagination(this.context)
        }
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