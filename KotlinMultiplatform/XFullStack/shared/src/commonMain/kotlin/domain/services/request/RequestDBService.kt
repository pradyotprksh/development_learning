package domain.services.request

import core.models.realm.RequestsDB
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface RequestDBService {
    fun getAllRequests(): Flow<ResultsChange<RequestsDB>>

    suspend fun deleteRequest(id: String)

    suspend fun saveRequests(request: RequestsDB): RequestsDB
}