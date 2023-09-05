package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.Berry
import com.pradyotprkshpokedex.features.berries.resource.BerryResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BerriesController(
    private val berryService: BerryService,
) {
    suspend fun getBerriesByPagination(context: ApplicationCall, berryResource: BerryResource.Pagination) {
        context.respond(status = HttpStatusCode.OK, "getBerriesByPagination")
    }

    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use berry/pagination route to get results in paginated format.
     */
    suspend fun getAllBerries(context: ApplicationCall) {
        coroutineScope {
            val channels = Channel<Berry>()
            var count: Int
            var next: String? = null
            do {
                val berries = berryService.getAllBerries(path = next)
                count = berries.count ?: 0

                berries.results.forEach { result ->
                    result.url?.let { url ->
                        launch {
                            delay(1)
                            channels.send(
                                berryService.getBerryDetails(id = 0, path = url)
                            )
                        }
                    }
                }

                next = berries.next
            } while (next != null)

            val berries = mutableListOf<Berry>()
            repeat(count) {
                berries.add(channels.receive())
            }

            context.respond(status = HttpStatusCode.OK, berries)
        }
    }

    suspend fun getBerryDetails(context: ApplicationCall, berryResource: BerryResource.Id) {
        if (berryResource.isValid) {
            context.respond(status = HttpStatusCode.OK, berryService.getBerryDetails(id = berryResource.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    suspend fun getAllBerryFirmness(context: ApplicationCall, firmnesses: BerryResource.BerryFirmness) {
        context.respond(status = HttpStatusCode.OK, "getAllBerryFirmness")
    }

    suspend fun getBerryFirmnessDetails(context: ApplicationCall, firmnesses: BerryResource.BerryFirmness.Id) {
        context.respond(status = HttpStatusCode.OK, "getBerryFirmnessDetails")
    }

    suspend fun getBerryFirmnessByPagination(context: ApplicationCall, firmnesses: BerryResource.BerryFirmness.Pagination) {
        context.respond(status = HttpStatusCode.OK, "getBerryFirmnessByPagination")
    }

    suspend fun getAllBerryFlavor(context: ApplicationCall, Flavor: BerryResource.BerryFlavor) {
        context.respond(status = HttpStatusCode.OK, "getAllBerryFlavor")
    }

    suspend fun getBerryFlavorDetails(context: ApplicationCall, Flavor: BerryResource.BerryFlavor.Id) {
        context.respond(status = HttpStatusCode.OK, "getBerryFlavorDetails")
    }

    suspend fun getBerryFlavorByPagination(context: ApplicationCall, Flavor: BerryResource.BerryFlavor.Pagination) {
        context.respond(status = HttpStatusCode.OK, "getBerryFlavorByPagination")
    }
}