package domain.services.tweet

import data.response.TweetsResponse

interface TweetDBService {
    suspend fun saveAllTweets(tweetsResponse: TweetsResponse)
}