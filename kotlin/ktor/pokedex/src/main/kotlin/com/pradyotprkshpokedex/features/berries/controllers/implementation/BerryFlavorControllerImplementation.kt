package com.pradyotprkshpokedex.features.berries.controllers.implementation

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.BerryFlavor
import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.features.berries.controllers.BerryFlavorController
import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BerryFlavorControllerImplementation(
    private val berryService: BerryService,
) : BerryFlavorController {
    override suspend fun getAllBerryFlavor(context: ApplicationCall, flavor: BerriesResource.BerryFlavor) {
        val allBerryFlavor = berryService.getBerriesFlavorByPagination(offset = 0, limit = Int.MAX_VALUE)
        respondWithBerriesFlavorDetails(context, allBerryFlavor)
    }

    override suspend fun getBerryFlavorDetails(context: ApplicationCall, flavor: BerriesResource.BerryFlavor.Id) {
        if (flavor.isValid) {
            context.respond(status = HttpStatusCode.OK, berryService.getBerryFlavorDetails(id = flavor.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getBerryFlavorByPagination(
        context: ApplicationCall,
        flavor: BerriesResource.BerryFlavor.Pagination
    ) {
        if (flavor.isValid) {
            val berryFlavor =
                berryService.getBerriesFlavorByPagination(offset = flavor.offset, limit = flavor.limit)
            if (flavor.withDetails) {
                respondWithBerriesFlavorDetails(context, berryFlavor)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    berryFlavor
                )
            }
        } else {
            throw ParametersInvalidException(
                invalidParameters = listOf(
                    Paths.Parameters.OFFSET,
                    Paths.Parameters.LIMIT
                )
            )
        }
    }

    private suspend fun respondWithBerriesFlavorDetails(context: ApplicationCall, berryFlavor: Pagination) {
        coroutineScope {
            val count = berryFlavor.results.size
            val channels = Channel<BerryFlavor>()
            berryFlavor.results.forEach { result ->
                result.url?.let { url ->
                    launch {
                        delay(1)
                        channels.send(
                            berryService.getBerryFlavorDetails(id = 0, path = url)
                        )
                    }
                }
            }
            val berriesFlavorDetails = mutableListOf<BerryFlavor>()
            repeat(count) {
                berriesFlavorDetails.add(channels.receive())
            }

            context.respond(status = HttpStatusCode.OK, berriesFlavorDetails)
        }
    }
}