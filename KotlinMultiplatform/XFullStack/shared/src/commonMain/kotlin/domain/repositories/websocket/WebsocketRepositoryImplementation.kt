package domain.repositories.websocket

import domain.services.websocket.WebsocketRemoteService

class WebsocketRepositoryImplementation(
    private val websocketRemoteService: WebsocketRemoteService,
) : WebsocketRepository {
    override suspend fun connectAndListen() = websocketRemoteService.connectAndListen()
}