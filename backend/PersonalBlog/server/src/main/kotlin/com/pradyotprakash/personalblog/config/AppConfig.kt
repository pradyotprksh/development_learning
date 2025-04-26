package com.pradyotprakash.personalblog.config

import com.pradyotprakash.personalblog.config.plugins.configureAdministration
import com.pradyotprakash.personalblog.config.plugins.configureMonitoring
import com.pradyotprakash.personalblog.config.plugins.configureResource
import com.pradyotprakash.personalblog.config.plugins.configureRouting
import com.pradyotprakash.personalblog.config.plugins.configureSerialization
import com.pradyotprakash.personalblog.utils.Constants.Server.HOST
import com.pradyotprakash.personalblog.utils.Constants.Server.PORT
import io.ktor.server.application.Application
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.ApplicationEngineFactory
import io.ktor.server.engine.BaseApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun setup() = server(Netty)

private fun server(
    engine: ApplicationEngineFactory<BaseApplicationEngine, out ApplicationEngine.Configuration>,
) = embeddedServer(
    engine, host = HOST, port = PORT, module = Application::mainModule
)

fun Application.mainModule() {
    configureResource()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureAdministration()
}
