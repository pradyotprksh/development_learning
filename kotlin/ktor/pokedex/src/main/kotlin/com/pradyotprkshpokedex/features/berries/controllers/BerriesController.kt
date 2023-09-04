package com.pradyotprkshpokedex.features.berries.controllers

import com.pradyotprkshpokedex.utils.Paths
import io.ktor.server.application.*
import io.ktor.server.response.*

class BerriesController {
    suspend fun getBerriesByPagination(context: ApplicationCall) {
        val offset = context.parameters[Paths.OFFSET]
        val limit = context.parameters[Paths.LIMIT]

        context.respondText("${Paths.Berries.BERRY}/${Paths.Berries.BERRY_PAGINATION} $offset $limit")
    }
}