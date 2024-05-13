package com.pradyotprakash.xfullstack.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.utils.Constants.Jwt.REALM
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.engine.ShutDownUrl
import io.ktor.server.plugins.callid.CallId
import io.ktor.server.plugins.callid.callIdMdc
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.path
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing
import org.slf4j.event.Level

fun Application.configureSecurity(
    config: TokenConfig
) {
    authentication {
        jwt {
            realm = REALM
            verifier(
                JWT.require(Algorithm.HMAC256(config.secret)).withAudience(config.audience)
                    .withIssuer(config.issuer).build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(config.audience)) JWTPrincipal(credential.payload)
                else null
            }
        }
    }
}

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

fun Application.configureAdministration() {
    install(ShutDownUrl.ApplicationCallPlugin) {
        shutDownUrl = "/shutdown/pradyotprksh"
        exitCodeSupplier = { 0 }
    }
}

fun Application.configureStatusPages() {
    install(StatusPages) {}
}

fun Application.configureResource() {
    install(Resources)
}

fun Application.configureRouting() {
    routing {}
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}