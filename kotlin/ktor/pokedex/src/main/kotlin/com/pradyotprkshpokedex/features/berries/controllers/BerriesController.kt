package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.features.berries.resource.Berry
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class BerriesController {
    suspend fun getBerriesByPagination(context: ApplicationCall, berry: Berry.Pagination) {
        context.respond(status = HttpStatusCode.OK, "getBerriesByPagination")
    }

    suspend fun getAllBerries(context: ApplicationCall, berry: Berry) {
        context.respond(status = HttpStatusCode.OK, "getAllBerries")
    }

    suspend fun getBerryDetails(context: ApplicationCall, berry: Berry.Id) {
        context.respond(status = HttpStatusCode.OK, "getBerryDetails")
    }

    suspend fun getAllBerryFirmness(context: ApplicationCall, firmnesses: Berry.BerryFirmness) {
        context.respond(status = HttpStatusCode.OK, "getAllBerryFirmness")
    }

    suspend fun getBerryFirmnessDetails(context: ApplicationCall, firmnesses: Berry.BerryFirmness.Id) {
        context.respond(status = HttpStatusCode.OK, "getBerryFirmnessDetails")
    }

    suspend fun getBerryFirmnessByPagination(context: ApplicationCall, firmnesses: Berry.BerryFirmness.Pagination) {
        context.respond(status = HttpStatusCode.OK, "getBerryFirmnessByPagination")
    }

    suspend fun getAllBerryFlavor(context: ApplicationCall, Flavor: Berry.BerryFlavor) {
        context.respond(status = HttpStatusCode.OK, "getAllBerryFlavor")
    }

    suspend fun getBerryFlavorDetails(context: ApplicationCall, Flavor: Berry.BerryFlavor.Id) {
        context.respond(status = HttpStatusCode.OK, "getBerryFlavorDetails")
    }

    suspend fun getBerryFlavorByPagination(context: ApplicationCall, Flavor: Berry.BerryFlavor.Pagination) {
        context.respond(status = HttpStatusCode.OK, "getBerryFlavorByPagination")
    }
}