package domain.services.tweet

import data.response.TweetsResponse
import data.response.XFullStackResponse

interface TweetRemoteService {
    suspend fun getAllTweets(): XFullStackResponse<TweetsResponse>
}