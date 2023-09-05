package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.berries.resource.Berry
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.berries(berriesController: BerriesController) {
    get<Berry> { berriesController.getAllBerries(this.context, it) }
    get<Berry.Id> { berriesController.getBerryDetails(this.context, it) }
    get<Berry.Pagination> { berriesController.getBerriesByPagination(this.context, it) }

    get<Berry.BerryFirmness> { berriesController.getAllBerryFirmness(this.context, it) }
    get<Berry.BerryFirmness.Id> { berriesController.getBerryFirmnessDetails(this.context, it) }
    get<Berry.BerryFirmness.Pagination> { berriesController.getBerryFirmnessByPagination(this.context, it) }

    get<Berry.BerryFlavor> { berriesController.getAllBerryFlavor(this.context, it) }
    get<Berry.BerryFlavor.Id> { berriesController.getBerryFlavorDetails(this.context, it) }
    get<Berry.BerryFlavor.Pagination> { berriesController.getBerryFlavorByPagination(this.context, it) }
}