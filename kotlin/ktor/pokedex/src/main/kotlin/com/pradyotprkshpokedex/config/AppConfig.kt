package com.pradyotprkshpokedex.config

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

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
    configureAdministration()
    configureMonitoring()
    configureHTTP()
    configureStatusPages()
    configureResource()
    configureRouting()
    configureSerialization()
}
