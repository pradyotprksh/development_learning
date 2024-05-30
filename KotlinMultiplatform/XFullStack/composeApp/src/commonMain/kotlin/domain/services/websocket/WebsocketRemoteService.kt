package domain.services.websocket

import kotlinx.coroutines.flow.Flow

interface WebsocketRemoteService {
    suspend fun connectAndListen(): Flow<String?>
}