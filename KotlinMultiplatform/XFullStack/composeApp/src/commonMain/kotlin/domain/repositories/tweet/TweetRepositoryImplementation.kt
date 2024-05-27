package domain.repositories.tweet

import core.models.response.ClientResponse
import data.response.TweetsResponse
import data.response.XFullStackResponse
import domain.services.tweet.TweetDBService
import domain.services.tweet.TweetRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TweetRepositoryImplementation(
    private val tweetDBService: TweetDBService,
    private val tweetRemoteService: TweetRemoteService,
) : TweetRepository {
    override suspend fun getAllTweets(): Flow<ClientResponse<out XFullStackResponse<TweetsResponse>>> =
        flow {

        }
}