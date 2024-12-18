package com.pradyotprakash.xfullstack.config

import com.pradyotprakash.xfullstack.config.plugins.configureAdministration
import com.pradyotprakash.xfullstack.config.plugins.configureMonitoring
import com.pradyotprakash.xfullstack.config.plugins.configureResource
import com.pradyotprakash.xfullstack.config.plugins.configureRouting
import com.pradyotprakash.xfullstack.config.plugins.configureSecurity
import com.pradyotprakash.xfullstack.config.plugins.configureSerialization
import com.pradyotprakash.xfullstack.config.plugins.configureStatusPages
import com.pradyotprakash.xfullstack.config.plugins.configureWebSockets
import io.ktor.server.application.Application
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.ApplicationEngineFactory
import io.ktor.server.engine.BaseApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import utils.Constants.Server.HOST
import utils.Constants.Server.PORT

fun setup(): BaseApplicationEngine = server(Netty)

private fun server(
    engine: ApplicationEngineFactory<BaseApplicationEngine, out ApplicationEngine.Configuration>,
): BaseApplicationEngine {
    return embeddedServer(
        engine, host = HOST, port = PORT, module = Application::mainModule
    )
}

fun Application.mainModule() {
    configureSecurity()
    configureResource()
    configureWebSockets()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureAdministration()
    configureStatusPages()
}