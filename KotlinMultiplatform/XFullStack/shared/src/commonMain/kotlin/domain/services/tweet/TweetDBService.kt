package domain.services.tweet

import core.models.realm.TweetDB
import core.models.realm.TweetRequestsDB
import core.models.request.TweetRequest
import core.models.response.TweetResponse
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface TweetDBService {
    suspend fun saveAllTweets(tweetResponse: List<TweetResponse>): List<TweetDB>

    fun getAllTweets(): Flow<ResultsChange<TweetDB>>

    fun getAllTweetRequests(): Flow<ResultsChange<TweetRequestsDB>>

    suspend fun deleteTweetRequest(id: String)

    fun getTweetById(id: String): TweetDB?

    suspend fun saveTweetRequests(tweetRequest: List<TweetRequest>): TweetRequestsDB
}