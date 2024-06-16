package com.pradyotprakash.xfullstack.features.websockets

import core.exception.UserDetailsNotFound
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.routing.Routing
import io.ktor.server.websocket.webSocket
import kotlinx.coroutines.delay
import utils.Constants.Keys.USER_ID
import utils.Constants.Paths.Websockets.WEBSOCKETS
import utils.Constants.SuccessCode.WEBSOCKET_CONNECTION_SUCCESS

fun Routing.websockets() {
    authenticate {
        webSocket(
            WEBSOCKETS,
        ) {
            val principal = call.principal<JWTPrincipal>()
            val connectionBy =
                principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()
            Connections.addConnections(userId = connectionBy, session = this)
            while (true) {
                delay(1500)
                Connections.sendMessageToAll(WEBSOCKET_CONNECTION_SUCCESS)
            }
        }
    }
}