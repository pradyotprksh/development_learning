package domain.repositories.tweet

import core.models.realm.TweetDB
import core.models.response.ClientResponse
import data.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface TweetRepository {
    suspend fun getAllTweets(
        page: Int = 1,
    ): Flow<ClientResponse<out XFullStackResponse<List<TweetDB>>>>
}