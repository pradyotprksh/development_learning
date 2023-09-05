package com.pradyotprkshpokedex.features.berries.controllers.implementation

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.service.BerryService
import com.pradyotprkshpokedex.domain.modal.Berries
import com.pradyotprkshpokedex.domain.modal.Berry
import com.pradyotprkshpokedex.features.berries.controllers.BerryController
import com.pradyotprkshpokedex.features.berries.resource.BerriesResource
import com.pradyotprkshpokedex.utils.Paths
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BerryControllerImplementation(
    private val berryService: BerryService,
) : BerryController {
    override suspend fun getBerriesByPagination(context: ApplicationCall, berriesResource: BerriesResource.Pagination) {
        if (berriesResource.isValid) {
            val berries =
                berryService.getBerriesByPagination(offset = berriesResource.offset, limit = berriesResource.limit)
            if (berriesResource.withDetails) {
                respondWithBerriesDetails(context, berries)
            } else {
                context.respond(
                    status = HttpStatusCode.OK,
                    berries
                )
            }
        } else {
            throw ParametersInvalidException(invalidParameters = listOf(Paths.Parameters.OFFSET, Paths.Parameters.LIMIT))
        }
    }

    override suspend fun getAllBerries(context: ApplicationCall) {
        val allBerries = berryService.getBerriesByPagination(offset = 0, limit = Int.MAX_VALUE)
        respondWithBerriesDetails(context, allBerries)
    }

    override suspend fun getBerryDetails(context: ApplicationCall, berriesResource: BerriesResource.Id) {
        if (berriesResource.isValid) {
            context.respond(status = HttpStatusCode.OK, berryService.getBerryDetails(id = berriesResource.id))
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
}