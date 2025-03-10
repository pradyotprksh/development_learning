package com.pradyotprakash.xfullstack.features.websockets.connections

import io.ktor.server.websocket.DefaultWebSocketServerSession
import io.ktor.websocket.Frame
import java.util.Collections

object Connections {
    private val connections =
        Collections.synchronizedSet<Pair<String, DefaultWebSocketServerSession>?>(LinkedHashSet())

    fun addConnections(userId: String, session: DefaultWebSocketServerSession) {
        removeSession(userId)
        connections.add(userId to session)
    }

    private fun getAllSessions() = connections.map { it.second }

    private fun removeSession(userId: String) = connections.removeIf { it.first == userId }

    fun sendMessageToAll(message: String) {
        getAllSessions().forEach {
            it.outgoing.trySend(
                Frame.Text(
                    message
                ),
            )
        }
    }

    private fun getSessionFor(userId: String): DefaultWebSocketServerSession? {
        return connections.find { it.first == userId }?.second
    }

    fun sendMessageTo(userId: String, message: String) {
        getSessionFor(userId)?.outgoing?.trySend(
            Frame.Text(message)
        )
    }
}