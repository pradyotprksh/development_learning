package domain.repositories.websocket

import kotlinx.coroutines.flow.Flow

interface WebsocketRepository {
    suspend fun connectAndListen(): Flow<String?>
}