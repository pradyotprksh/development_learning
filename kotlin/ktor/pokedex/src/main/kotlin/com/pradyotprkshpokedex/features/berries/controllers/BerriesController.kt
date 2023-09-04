package com.pradyotprkshpokedex.features.berries.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class BerriesController {
    suspend fun getBerriesByPagination(context: ApplicationCall) {
        context.respond(status = HttpStatusCode.OK, "Pagination")
    }

    suspend fun getAllBerries(context: ApplicationCall) {
        context.respond(status = HttpStatusCode.OK, "Pagination")
    }

    suspend fun getBerryDetails(context: ApplicationCall) {
        context.respond(status = HttpStatusCode.OK, "Pagination")
    }
}