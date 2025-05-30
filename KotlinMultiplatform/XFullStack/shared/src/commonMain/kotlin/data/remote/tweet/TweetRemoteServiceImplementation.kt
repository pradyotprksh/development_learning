package data.remote.tweet

import core.models.request.TweetRequest
import core.models.request.XFullStackClientRequestDetails
import core.models.response.TweetResponse
import core.models.response.XFullStackResponse
import core.network.NetworkClient
import domain.services.tweet.TweetRemoteService
import utils.Constants.Keys.LIMIT
import utils.Constants.Keys.OPTION_ID
import utils.Constants.Keys.PAGE
import utils.Constants.Keys.TWEET_ID
import utils.Constants.Paths.Tweets.PAGINATE
import utils.Constants.Paths.Tweets.TWEET
import utils.Constants.Paths.Tweets.TWEET_VOTE

class TweetRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : TweetRemoteService {
    override suspend fun getAllTweets(
        page: Int,
        limit: Int,
    ): XFullStackResponse<List<TweetResponse>> {
        val response = networkClient.get<XFullStackResponse<List<TweetResponse>>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$TWEET$PAGINATE",
                queries = mapOf(
                    PAGE to page,
                    LIMIT to limit
                )
            )
        )

        return response.getOrThrow()
    }

    override suspend fun updateTweetPoll(
        tweetId: String,
        optionId: String,
    ): XFullStackResponse<Nothing> {
        val response = networkClient.post<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$TWEET$TWEET_VOTE",
                queries = mapOf(
                    TWEET_ID to tweetId,
                    OPTION_ID to optionId,
                )
            )
        )

        return response.getOrThrow()
    }

    override suspend fun uploadTweets(tweets: List<TweetRequest>): XFullStackResponse<Nothing> {
        val response = networkClient.post<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = TWEET,
                body = tweets,
            )
        )

        return response.getOrThrow()
    }
}