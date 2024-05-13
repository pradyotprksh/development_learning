package com.pradyotprakash.xfullstack.config

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.utils.Constants.Jwt.AUDIENCE
import com.pradyotprakash.xfullstack.utils.Constants.Jwt.ISSUER
import com.pradyotprakash.xfullstack.utils.Constants.Server.HOST
import com.pradyotprakash.xfullstack.utils.Constants.Server.PORT
import io.ktor.server.application.Application
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.ApplicationEngineFactory
import io.ktor.server.engine.BaseApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.kodein.di.instance

fun setup(): BaseApplicationEngine = server(Netty)

private fun server(
    engine: ApplicationEngineFactory<BaseApplicationEngine, out ApplicationEngine.Configuration>
): BaseApplicationEngine {
    return embeddedServer(
        engine, host = HOST, port = PORT, module = Application::mainModule
    )
}

fun Application.mainModule() {
    val userDataSource by ModulesConfig.di.instance<UserDataSource>()
    val tokenService by ModulesConfig.di.instance<TokenService>()
    val tokenConfig = TokenConfig(
        issuer = ISSUER,
        audience = AUDIENCE,
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET"),
    )
    val hashingService by ModulesConfig.di.instance<HashingService>()

    configureAdministration()
    configureMonitoring()
    configureStatusPages()
    configureResource()
    configureRouting()
    configureSerialization()
    configureSecurity(tokenConfig)
}