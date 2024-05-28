package domain.services.tweet

import core.models.realm.TweetDB
import data.response.TweetsResponse
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface TweetDBService {
    suspend fun saveAllTweets(tweetsResponse: List<TweetsResponse>): List<TweetDB>

    suspend fun getAllTweets(): Flow<ResultsChange<TweetDB>>
}