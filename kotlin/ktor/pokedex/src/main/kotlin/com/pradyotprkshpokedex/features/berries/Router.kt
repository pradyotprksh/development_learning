package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.berries(berriesController: BerriesController) {
    get<BerriesResource> { berriesController.getAllBerries(this.context) }
    get<BerriesResource.Id> { berriesController.getBerryDetails(this.context, it) }
    get<BerriesResource.Pagination> { berriesController.getBerriesByPagination(this.context, it) }

    get<BerriesResource.BerryFirmness> { berriesController.getAllBerryFirmness(this.context, it) }
    get<BerriesResource.BerryFirmness.Id> { berriesController.getBerryFirmnessDetails(this.context, it) }
    get<BerriesResource.BerryFirmness.Pagination> { berriesController.getBerryFirmnessByPagination(this.context, it) }

    get<BerriesResource.BerryFlavor> { berriesController.getAllBerryFlavor(this.context, it) }
    get<BerriesResource.BerryFlavor.Id> { berriesController.getBerryFlavorDetails(this.context, it) }
    get<BerriesResource.BerryFlavor.Pagination> { berriesController.getBerryFlavorByPagination(this.context, it) }
}