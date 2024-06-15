package domain.repositories.request

import core.models.realm.RequestsDB
import core.models.request.TweetRequest
import core.models.response.ClientResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface RequestRepository {
    suspend fun saveTweetRequests(requests: List<TweetRequest>)

    suspend fun onRequestChanges(): Flow<List<RequestsDB>>

    suspend fun initiateRequest(requestDb: RequestsDB): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun saveFollowRequest(followingId: String)

    suspend fun saveBookmarkRequest(tweetId: String)
}