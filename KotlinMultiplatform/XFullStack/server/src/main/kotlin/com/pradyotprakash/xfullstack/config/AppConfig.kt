package com.pradyotprakash.xfullstack.config

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
        host = "192.168.1.37",
        port = SERVER_PORT,
        module = Application::mainModule
    )
}

fun Application.mainModule() {
    configureAdministration()
    configureMonitoring()
    configureStatusPages()
    configureResource()
    configureRouting()
    configureSerialization()
}