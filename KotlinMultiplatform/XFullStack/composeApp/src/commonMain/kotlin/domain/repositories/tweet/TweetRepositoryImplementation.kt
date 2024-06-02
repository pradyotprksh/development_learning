package domain.repositories.tweet

import core.models.realm.TweetDB
import core.models.response.ClientResponse
import data.request.TweetRequest
import data.response.XFullStackResponse
import domain.services.tweet.TweetDBService
import domain.services.tweet.TweetRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Localization
import utils.XFullStackResponseStatus

class TweetRepositoryImplementation(
    private val tweetDBService: TweetDBService,
    private val tweetRemoteService: TweetRemoteService,
) : TweetRepository {
    override suspend fun updateAllTweets(
        page: Int,
        limit: Int,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>> = flow {
        emit(ClientResponse.Loading)
        try {
            val response = tweetRemoteService.getAllTweets(page = page)
            if (response.status == XFullStackResponseStatus.Success) {
                response.data?.let { tweetsResponse ->
                    tweetDBService.saveAllTweets(tweetsResponse)
                }
            }

            emit(
                ClientResponse.Success(
                    XFullStackResponse(
                        status = response.status,
                        message = response.message,
                        code = response.code,
                        data = null,
                    )
                )
            )
        } catch (e: Exception) {
            emit(
                ClientResponse.Error(
                    message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                    errorCode = DEFAULT_ERROR_CODE,
                ),
            )
        }
        emit(ClientResponse.Idle)
    }

    override suspend fun allTweetsChanges(): Flow<List<TweetDB>> {
        return tweetDBService.getAllTweets().map { it.list }
    }

    override suspend fun updateTweetPoll(tweetId: String, pollId: String) = flow {
        try {
            val response = tweetRemoteService.updateTweetPoll(tweetId, pollId)
            if (response.status == XFullStackResponseStatus.Success) {
                emit(
                    ClientResponse.Success(response)
                )
            } else {
                emit(
                    ClientResponse.Error(
                        message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = response.code ?: DEFAULT_ERROR_CODE,
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                ClientResponse.Error(
                    message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                    errorCode = DEFAULT_ERROR_CODE,
                ),
            )
        }
        emit(ClientResponse.Idle)
    }

    override suspend fun uploadTweets(tweets: List<TweetRequest>) = flow {
        try {
            val response = tweetRemoteService.uploadTweets(tweets)
            if (response.status == XFullStackResponseStatus.Success) {
                emit(
                    ClientResponse.Success(response)
                )
            } else {
                emit(
                    ClientResponse.Error(
                        message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = response.code ?: DEFAULT_ERROR_CODE,
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                ClientResponse.Error(
                    message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                    errorCode = DEFAULT_ERROR_CODE,
                ),
            )
        }
        emit(ClientResponse.Idle)
    }
}