package com.pradyotprkshpokedex.config

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.exception.PokedexException
import com.pradyotprkshpokedex.features.berries.berries
import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.contests.contests
import com.pradyotprkshpokedex.features.contests.controllers.ContestsController
import com.pradyotprkshpokedex.features.encounters.controllers.EncountersController
import com.pradyotprkshpokedex.features.encounters.encounters
import com.pradyotprkshpokedex.features.evolution.controllers.EvolutionsController
import com.pradyotprkshpokedex.features.evolution.evolution
import com.pradyotprkshpokedex.features.games.controllers.GamesController
import com.pradyotprkshpokedex.features.games.games
import com.pradyotprkshpokedex.features.items.items
import com.pradyotprkshpokedex.features.locations.locations
import com.pradyotprkshpokedex.features.machines.controllers.MachinesController
import com.pradyotprkshpokedex.features.machines.machines
import com.pradyotprkshpokedex.features.moves.moves
import com.pradyotprkshpokedex.features.pokemon.pokemon
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.ShutDownUrl
import io.ktor.server.plugins.callid.CallId
import io.ktor.server.plugins.callid.callIdMdc
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.request.path
import io.ktor.server.resources.Resources
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing
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
    val contestsController by ModulesConfig.kodein.instance<ContestsController>()
    val encountersController by ModulesConfig.kodein.instance<EncountersController>()
    val evolutionsController by ModulesConfig.kodein.instance<EvolutionsController>()
    val gamesController by ModulesConfig.kodein.instance<GamesController>()
    val machinesController by ModulesConfig.kodein.instance<MachinesController>()

    routing {
        berries(berriesController = berriesController)
        contests(contestsController = contestsController)
        encounters(encountersController = encountersController)
        evolution(evolutionsController = evolutionsController)
        games(gamesController = gamesController)
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