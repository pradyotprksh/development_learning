package domain.repositories.request

import core.models.realm.RequestsDB
import core.models.request.TweetRequest
import core.parser.parseToRequestDb
import domain.services.request.RequestDBService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RequestRepositoryImplementation(
    private val requestDBService: RequestDBService,
) : RequestRepository {
    override suspend fun saveTweetRequests(requests: List<TweetRequest>) {
        val request = requests.parseToRequestDb()
        requestDBService.saveRequests(request)
    }

    override suspend fun onRequestChanges(): Flow<List<RequestsDB>> {
        return requestDBService.getAllRequests().map { it.list }
    }

    override suspend fun initiateRequest(requestDb: RequestsDB) {
        TODO("Not yet implemented")
    }
}