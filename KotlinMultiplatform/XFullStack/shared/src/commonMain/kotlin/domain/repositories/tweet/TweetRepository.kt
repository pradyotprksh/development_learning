package domain.repositories.tweet

import core.models.realm.TweetRequestsDB
import core.models.request.TweetRequest
import core.models.response.ClientResponse
import core.models.response.TweetResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow
import utils.Constants.ConstValues.DEFAULT_PAGINATE_LIMIT

interface TweetRepository {
    suspend fun updateAllTweets(
        page: Int = 1,
        limit: Int = DEFAULT_PAGINATE_LIMIT,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun allTweetsChanges(): Flow<List<TweetResponse>>

    suspend fun getTweetChanges(id: String): Flow<TweetResponse?>

    suspend fun getAllTweetsReplyFor(id: String): Flow<List<TweetResponse>>

    suspend fun allTweetRequestChanges(): Flow<List<TweetRequestsDB>>

    suspend fun deleteTweetRequest(id: String)

    suspend fun updateTweetPoll(
        tweetId: String,
        pollId: String,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun saveTweetRequests(tweets: List<TweetRequest>)

    suspend fun uploadTweets(
        tweetRequestsDb: TweetRequestsDB,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun getTweetDetails(
        tweetId: String,
    ): TweetResponse?

    suspend fun deleteTweetById(id: String)
}