package domain.repositories.tweet

import core.models.response.ClientResponse
import data.response.TweetsResponse
import data.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface TweetRepository {
    suspend fun getAllTweets(): Flow<ClientResponse<out XFullStackResponse<TweetsResponse>>>
}