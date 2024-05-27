package domain.services.tweet

import core.models.realm.TweetDB
import data.response.TweetsResponse

interface TweetDBService {
    suspend fun saveAllTweets(tweetsResponse: List<TweetsResponse>): List<TweetDB>

    suspend fun getAllTweets(): List<TweetDB>
}