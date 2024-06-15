package domain.repositories.tweet

import core.models.response.ClientResponse
import core.models.response.TweetResponse
import core.models.response.XFullStackResponse
import core.parser.parseToTweetResponse
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

    override suspend fun allTweetsChanges(): Flow<List<TweetResponse>> {
        return tweetDBService.getAllTweets()
            .map { dbResult -> dbResult.list.map { db -> db.parseToTweetResponse() } }
    }

    override suspend fun getTweetChanges(id: String): Flow<TweetResponse?> {
        return tweetDBService.getTweetChanges(id)
            .map { dbResult -> dbResult.obj?.parseToTweetResponse() }
    }

    override suspend fun getAllTweetsReplyFor(id: String): Flow<List<TweetResponse>> {
        return tweetDBService.getAllTweetsReplyFor(id)
            .map { dbResult -> dbResult.list.map { db -> db.parseToTweetResponse() } }
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

    override suspend fun getTweetDetails(tweetId: String): TweetResponse? {
        return tweetDBService.getTweetById(tweetId)?.parseToTweetResponse()
    }

    override suspend fun deleteTweetById(id: String) {
        tweetDBService.deleteTweetById(id)
    }

    override suspend fun getAllUserPosts(userId: String): Flow<List<TweetResponse>> {
        return tweetDBService.getAllUserPosts(userId)
            .map { dbResult -> dbResult.list.map { db -> db.parseToTweetResponse() } }
    }

    override suspend fun getAllUserLikes(userId: String): Flow<List<TweetResponse>> {
        return tweetDBService.getAllUserLikes(userId)
            .map { dbResult -> dbResult.list.map { db -> db.parseToTweetResponse() } }
    }

    override suspend fun getAllUserReplies(userId: String): Flow<List<TweetResponse>> {
        return tweetDBService.getAllUserReplies(userId)
            .map { dbResult -> dbResult.list.map { db -> db.parseToTweetResponse() } }
    }

    override suspend fun getAllUserMedia(userId: String): Flow<List<TweetResponse>> {
        return tweetDBService.getAllUserMedia(userId)
            .map { dbResult -> dbResult.list.map { db -> db.parseToTweetResponse() } }
    }
}