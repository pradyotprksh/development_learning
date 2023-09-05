package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.berries.resource.BerryResource
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.berries(berriesController: BerriesController) {
    get<BerryResource> { berriesController.getAllBerries(this.context) }
    get<BerryResource.Id> { berriesController.getBerryDetails(this.context, it) }
    get<BerryResource.Pagination> { berriesController.getBerriesByPagination(this.context, it) }

    get<BerryResource.BerryFirmness> { berriesController.getAllBerryFirmness(this.context, it) }
    get<BerryResource.BerryFirmness.Id> { berriesController.getBerryFirmnessDetails(this.context, it) }
    get<BerryResource.BerryFirmness.Pagination> { berriesController.getBerryFirmnessByPagination(this.context, it) }

    get<BerryResource.BerryFlavor> { berriesController.getAllBerryFlavor(this.context, it) }
    get<BerryResource.BerryFlavor.Id> { berriesController.getBerryFlavorDetails(this.context, it) }
    get<BerryResource.BerryFlavor.Pagination> { berriesController.getBerryFlavorByPagination(this.context, it) }
}