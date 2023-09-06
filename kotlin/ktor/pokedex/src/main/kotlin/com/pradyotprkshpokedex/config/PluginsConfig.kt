package com.pradyotprkshpokedex.config

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.exception.PokedexException
import com.pradyotprkshpokedex.features.berries.berries
import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.contests.contests
import com.pradyotprkshpokedex.features.encounters.encounters
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionsController
import com.pradyotprkshpokedex.features.evolution.evolution
import com.pradyotprkshpokedex.features.games.games
import com.pradyotprkshpokedex.features.items.items
import com.pradyotprkshpokedex.features.locations.locations
import com.pradyotprkshpokedex.features.machines.controllers.MachinesController
import com.pradyotprkshpokedex.features.machines.machines
import com.pradyotprkshpokedex.features.moves.moves
import com.pradyotprkshpokedex.features.pokemon.pokemon
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.generic.instance
import org.slf4j.event.Level

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
        callIdMdc("call-id")
    }
    install(CallId) {
        header(HttpHeaders.XRequestId)
        verify { callId: String ->
            callId.isNotEmpty()
        }
    }
}

fun Application.configureHTTP() {
    routing {
        swaggerUI(path = "openapi")
    }
}

fun Application.configureAdministration() {
    install(ShutDownUrl.ApplicationCallPlugin) {
        shutDownUrl = "/shutdown/pradyotprksh"
        exitCodeSupplier = { 0 }
    }
}

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<PokedexException> { call, cause ->
            when (cause) {
                is ParametersInvalidException -> call.respondText(
                    text = cause.message,
                    status = HttpStatusCode.BadRequest
                )

                is PokeApiException -> call.respondText(
                    text = cause.message,
                    status = HttpStatusCode.InternalServerError
                )
            }
        }
    }
}

fun Application.configureResource() {
    install(Resources)
}

fun Application.configureRouting() {
    val berriesController by ModulesConfig.kodein.instance<BerriesController>()
    val machinesController by ModulesConfig.kodein.instance<MachinesController>()
    val evolutionsController by ModulesConfig.kodein.instance<EvolutionsController>()

    routing {
        berries(berriesController = berriesController)
        contests()
        encounters()
        evolution(evolutionsController = evolutionsController)
        games()
        items()
        locations()
        machines(machinesController = machinesController)
        moves()
        pokemon()
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
