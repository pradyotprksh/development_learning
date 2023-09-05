package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.Berries
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
        if (berryResource.isValid) {
            val berries =
                berryService.getBerriesByPagination(offset = berryResource.offset, limit = berryResource.limit)
            if (berryResource.withDetails) {
                respondWithBerriesDetails(context, berries)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    berries
                )
            }
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    private suspend fun respondWithBerriesDetails(context: ApplicationCall, berries: Berries) {
        coroutineScope {
            val count = berries.results.size
            val channels = Channel<Berry>()
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
            val berriesDetails = mutableListOf<Berry>()
            repeat(count) {
                berriesDetails.add(channels.receive())
            }

            context.respond(status = HttpStatusCode.OK, berriesDetails)
        }
    }

    /**
     * USE IT AT YOUR OWN RISK
     *
     * Using this might leads to Connection Reset / Timeout error.
     * Better to use berry/pagination route to get results in paginated format.
     */
    suspend fun getAllBerries(context: ApplicationCall) {
        coroutineScope {
            val allBerries = berryService.getBerriesByPagination(offset = 0, limit = Int.MAX_VALUE)
            respondWithBerriesDetails(context, allBerries)
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

    suspend fun getBerryFirmnessByPagination(
        context: ApplicationCall,
        firmnesses: BerryResource.BerryFirmness.Pagination
    ) {
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