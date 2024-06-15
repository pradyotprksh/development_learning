package domain.repositories.request

import core.models.realm.RequestsDB
import core.models.request.TweetRequest
import kotlinx.coroutines.flow.Flow

interface RequestRepository {
    suspend fun saveTweetRequests(requests: List<TweetRequest>)

    suspend fun onRequestChanges(): Flow<List<RequestsDB>>

    suspend fun initiateRequest(requestDb: RequestsDB)
}