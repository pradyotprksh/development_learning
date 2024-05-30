package com.pradyotprakash.xfullstack.features.websockets

import io.ktor.server.websocket.DefaultWebSocketServerSession
import java.util.Collections

object Connections {
    private val connections =
        Collections.synchronizedSet<Pair<String, DefaultWebSocketServerSession>?>(LinkedHashSet())

    fun addConnections(userId: String, session: DefaultWebSocketServerSession) {
        removeSession(userId)
        connections.add(userId to session)
    }

    fun getAllSessions() = connections.map { it.second }

    fun removeSession(userId: String) = connections.removeIf { it.first == userId }
}