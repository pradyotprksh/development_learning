package com.pradyotprakash.unitconverter.config

import com.pradyotprakash.unitconverter.utils.Constants.Server.HOST
import com.pradyotprakash.unitconverter.utils.Constants.Server.PORT
import com.pradyotprakash.unitconverter.config.plugins.configureAdministration
import com.pradyotprakash.unitconverter.config.plugins.configureMonitoring
import com.pradyotprakash.unitconverter.config.plugins.configureResource
import com.pradyotprakash.unitconverter.config.plugins.configureRouting
import com.pradyotprakash.unitconverter.config.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.ApplicationEngineFactory
import io.ktor.server.engine.BaseApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun setup(): BaseApplicationEngine = server(Netty)

private fun server(
    engine: ApplicationEngineFactory<BaseApplicationEngine, out ApplicationEngine.Configuration>,
): BaseApplicationEngine {
    return embeddedServer(
        engine, host = HOST, port = PORT, module = Application::mainModule
    ).engine
}

fun Application.mainModule() {
    configureResource()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureAdministration()
}
