package com.pradyotprkshpokedex.features.berries

import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.berries(berriesController: BerriesController) {
    get<BerriesResource> { berriesController.getAll(this.context) }
    get<BerriesResource.Id> { berriesController.getDetails(this.context, it) }
    get<BerriesResource.Pagination> { berriesController.getByPagination(this.context, it) }

    get<BerriesResource.BerryFirmness> { berriesController.getAll(this.context, it) }
    get<BerriesResource.BerryFirmness.Id> { berriesController.getDetails(this.context, it) }
    get<BerriesResource.BerryFirmness.Pagination> { berriesController.getByPagination(this.context, it) }

    get<BerriesResource.BerryFlavor> { berriesController.getAll(this.context, it) }
    get<BerriesResource.BerryFlavor.Id> { berriesController.getDetails(this.context, it) }
    get<BerriesResource.BerryFlavor.Pagination> { berriesController.getByPagination(this.context, it) }
}