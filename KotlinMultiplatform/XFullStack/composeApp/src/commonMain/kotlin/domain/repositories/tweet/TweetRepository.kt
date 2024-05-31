package domain.repositories.tweet

import core.models.realm.TweetDB
import core.models.response.ClientResponse
import data.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow
import utils.Constants.ConstValues.DEFAULT_PAGINATE_LIMIT

interface TweetRepository {
    suspend fun updateAllTweets(
        page: Int = 1,
        limit: Int = DEFAULT_PAGINATE_LIMIT,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun allTweetsChanges(): Flow<List<TweetDB>>

    suspend fun updateTweetPoll(
        tweetId: String,
        pollId: String,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>
}