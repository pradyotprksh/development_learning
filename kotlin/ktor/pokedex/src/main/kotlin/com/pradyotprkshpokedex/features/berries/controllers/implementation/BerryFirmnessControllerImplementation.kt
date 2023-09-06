package com.pradyotprkshpokedex.features.berries.controllers.implementation

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.BerryFirmness
import com.pradyotprkshpokedex.domain.modal.BerryFirmnesses
import com.pradyotprkshpokedex.features.berries.controllers.BerryFirmnessController
import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BerryFirmnessControllerImplementation(
    private val berryService: BerryService,
) : BerryFirmnessController {
    override suspend fun getAllBerryFirmness(context: ApplicationCall, firmnesses: BerriesResource.BerryFirmness) {
        val allBerryFirmnesses = berryService.getBerriesFirmnessByPagination(offset = 0, limit = Int.MAX_VALUE)
        respondWithBerriesFirmnessesDetails(context, allBerryFirmnesses)
    }

    override suspend fun getBerryFirmnessDetails(
        context: ApplicationCall,
        firmnesses: BerriesResource.BerryFirmness.Id
    ) {
        if (firmnesses.isValid) {
            context.respond(status = HttpStatusCode.OK, berryService.getBerryFirmnessDetails(id = firmnesses.id))
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.ID))
        }
    }

    override suspend fun getBerryFirmnessByPagination(
        context: ApplicationCall,
        firmnesses: BerriesResource.BerryFirmness.Pagination
    ) {
        if (firmnesses.isValid) {
            val berryFirmnesses =
                berryService.getBerriesFirmnessByPagination(offset = firmnesses.offset, limit = firmnesses.limit)
            if (firmnesses.withDetails) {
                respondWithBerriesFirmnessesDetails(context, berryFirmnesses)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    berryFirmnesses
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

    private suspend fun respondWithBerriesFirmnessesDetails(
        context: ApplicationCall,
        berryFirmnesses: BerryFirmnesses
    ) {
        coroutineScope {
            val count = berryFirmnesses.results.size
            val channels = Channel<BerryFirmness>()
            berryFirmnesses.results.forEach { result ->
                result.url?.let { url ->
                    launch {
                        delay(1)
                        channels.send(
                            berryService.getBerryFirmnessDetails(id = 0, path = url)
                        )
                    }
                }
            }
            val berriesFirmnessDetails = mutableListOf<BerryFirmness>()
            repeat(count) {
                berriesFirmnessDetails.add(channels.receive())
            }

            context.respond(status = HttpStatusCode.OK, berriesFirmnessDetails)
        }
    }
}