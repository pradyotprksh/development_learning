package com.pradyotprkshpokedex.config

import io.ktor.server.application.Application
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.ApplicationEngineFactory
import io.ktor.server.engine.BaseApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

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
