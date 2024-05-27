package data.remote.tweet

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import data.response.TweetsResponse
import data.response.XFullStackResponse
import domain.services.tweet.TweetRemoteService
import utils.Constants.Keys.LIMIT
import utils.Constants.Keys.PAGE
import utils.Constants.Paths.Tweets.PAGINATE
import utils.Constants.Paths.Tweets.TWEET

class TweetRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : TweetRemoteService {
    override suspend fun getAllTweets(
        page: Int,
        limit: Int
    ): XFullStackResponse<List<TweetsResponse>> {
        val response = networkClient.get<XFullStackResponse<List<TweetsResponse>>>(
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
}