package domain.services.tweet

import core.models.realm.TweetDB
import core.models.realm.TweetRequestsDB
import data.request.TweetRequest
import data.response.TweetsResponse
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface TweetDBService {
    suspend fun saveAllTweets(tweetsResponse: List<TweetsResponse>): List<TweetDB>

    fun getAllTweets(): Flow<ResultsChange<TweetDB>>

    fun getAllTweetRequests(): Flow<ResultsChange<TweetRequestsDB>>

    suspend fun deleteTweetRequest(id: String)

    fun getTweetById(id: String): TweetDB?

    suspend fun saveTweetRequests(tweetRequest: List<TweetRequest>): TweetRequestsDB
}