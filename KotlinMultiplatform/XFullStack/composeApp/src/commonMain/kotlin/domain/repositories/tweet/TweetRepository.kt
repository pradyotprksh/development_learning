package domain.repositories.tweet

import core.models.realm.TweetDB
import core.models.realm.TweetRequestsDB
import core.models.response.ClientResponse
import data.request.TweetRequest
import data.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow
import utils.Constants.ConstValues.DEFAULT_PAGINATE_LIMIT

interface TweetRepository {
    suspend fun updateAllTweets(
        page: Int = 1,
        limit: Int = DEFAULT_PAGINATE_LIMIT,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun allTweetsChanges(): Flow<List<TweetDB>>

    suspend fun allTweetRequestChanges(): Flow<List<TweetRequestsDB>>

    suspend fun deleteTweetRequest(id: String)

    suspend fun updateTweetPoll(
        tweetId: String,
        pollId: String,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun saveTweetRequests(tweets: List<TweetRequest>)

    suspend fun uploadTweets(
        tweets: List<TweetRequest>
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>
}