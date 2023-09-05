package com.pradyotprkshpokedex.config

import com.pradyotprkshpokedex.core.exception.ParametersInvalidException
import com.pradyotprkshpokedex.core.exception.PokeApiException
import com.pradyotprkshpokedex.core.exception.PokedexException
import com.pradyotprkshpokedex.features.berries.berries
import com.pradyotprkshpokedex.features.berries.controllers.BerriesController
import com.pradyotprkshpokedex.features.contests.contests
import com.pradyotprkshpokedex.features.encounters.encounters
import com.pradyotprkshpokedex.features.evolution.evolution
import com.pradyotprkshpokedex.features.games.games
import com.pradyotprkshpokedex.features.items.items
import com.pradyotprkshpokedex.features.locations.locations
import com.pradyotprkshpokedex.features.machines.machines
import com.pradyotprkshpokedex.features.moves.moves
import com.pradyotprkshpokedex.features.pokemon.pokemon
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.generic.instance

const val SERVER_PORT = 8080

fun setup(): BaseApplicationEngine = server(Netty)

private fun server(
    engine: ApplicationEngineFactory<BaseApplicationEngine,
            out ApplicationEngine.Configuration>
): BaseApplicationEngine {
    return embeddedServer(
        engine,
        port = SERVER_PORT,
        module = Application::mainModule
    )
}

fun Application.mainModule() {
    configureStatusPages()
    configureResource()
    configureRouting()
    configureSerialization()
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

        exception<Throwable> { call, cause ->
            call.respondText(
                text = "${HttpStatusCode.InternalServerError.value}: $cause",
                status = HttpStatusCode.InternalServerError
            )
        }
    }
}

fun Application.configureResource() {
    install(Resources)
}

fun Application.configureRouting() {
    val berriesController by ModulesConfig.kodein.instance<BerriesController>()

    routing {
        berries(berriesController = berriesController)
        contests()
        encounters()
        evolution()
        games()
        items()
        locations()
        machines()
        moves()
        pokemon()
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
