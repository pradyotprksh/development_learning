package data.remote.tweet

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import data.response.TweetsResponse
import data.response.XFullStackResponse
import domain.services.tweet.TweetRemoteService
import utils.Constants.Paths.Tweets.TWEET

class TweetRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : TweetRemoteService {
    override suspend fun getAllTweets(): XFullStackResponse<TweetsResponse> {
        val response = networkClient.get<XFullStackResponse<TweetsResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = TWEET,
            )
        )

        return response.getOrThrow()
    }
}