package data.remote.websocket

import core.network.NetworkClient
import domain.services.websocket.WebsocketRemoteService

class WebsocketRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : WebsocketRemoteService {
    override suspend fun connectAndListen() = networkClient.websocket()
}