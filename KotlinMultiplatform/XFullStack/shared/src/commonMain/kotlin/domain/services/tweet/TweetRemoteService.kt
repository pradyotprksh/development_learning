package domain.services.tweet

import core.models.request.TweetRequest
import core.models.response.TweetsResponse
import core.models.response.XFullStackResponse
import utils.Constants.ConstValues.DEFAULT_PAGINATE_LIMIT

interface TweetRemoteService {
    suspend fun getAllTweets(
        page: Int = 1,
        limit: Int = DEFAULT_PAGINATE_LIMIT,
    ): XFullStackResponse<List<TweetsResponse>>

    suspend fun updateTweetPoll(
        tweetId: String,
        optionId: String,
    ): XFullStackResponse<Nothing>

    suspend fun uploadTweets(
        tweets: List<TweetRequest>
    ): XFullStackResponse<Nothing>
}