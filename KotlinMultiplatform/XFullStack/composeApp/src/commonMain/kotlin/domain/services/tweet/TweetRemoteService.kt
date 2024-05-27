package domain.services.tweet

import data.response.TweetsResponse
import data.response.XFullStackResponse

interface TweetRemoteService {
    suspend fun getAllTweets(
        page: Int = 1,
        limit: Int = 10,
    ): XFullStackResponse<List<TweetsResponse>>
}