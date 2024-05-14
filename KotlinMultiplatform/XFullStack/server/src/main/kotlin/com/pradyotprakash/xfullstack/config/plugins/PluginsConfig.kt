package com.pradyotprakash.xfullstack.config.plugins

import com.pradyotprakash.xfullstack.data.response.ErrorResponse
import com.pradyotprakash.xfullstack.data.response.XFullStackResponse
import core.exception.InvalidParameter
import core.exception.XFullStackException
import core.utils.ResponseStatus
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
import io.ktor.server.request.path
import io.ktor.server.resources.Resources
import io.ktor.server.response.respond
import kotlinx.serialization.json.Json
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

fun Application.configureAdministration() {
    install(ShutDownUrl.ApplicationCallPlugin) {
        shutDownUrl = "/shutdown/pradyotprksh"
        exitCodeSupplier = { 0 }
    }
}

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<XFullStackException> { call, cause ->
            when (cause) {
                is InvalidParameter -> call.respond(
                    HttpStatusCode.Conflict,
                    XFullStackResponse(
                        status = ResponseStatus.Error,
                        data = ErrorResponse(
                            message = cause.message,
                        )
                    )
                )
            }
        }
    }
}

fun Application.configureResource() {
    install(Resources)
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
            }
        )
    }
}