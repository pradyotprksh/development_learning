package domain.repositories.request

import core.models.realm.RequestsDB
import core.models.request.TweetRequest
import core.models.response.ClientResponse
import core.models.response.XFullStackResponse
import core.parser.parseToRequestDb
import core.parser.parseToTweetRequest
import domain.services.request.RequestDBService
import domain.services.tweet.TweetRemoteService
import domain.services.user.bookmark.UserBookmarkRemoteService
import domain.services.user.follow.UserFollowRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Constants.Keys.BOOKMARK_REQUEST
import utils.Constants.Keys.FOLLOW_REQUEST
import utils.Constants.Keys.TWEET_REQUEST
import utils.Localization
import utils.XFullStackResponseStatus

class RequestRepositoryImplementation(
    private val requestDBService: RequestDBService,
    private val tweetRemoteService: TweetRemoteService,
    private val userFollowRemoteService: UserFollowRemoteService,
    private val userBookmarkRemoteService: UserBookmarkRemoteService,
) : RequestRepository {
    override suspend fun saveTweetRequests(requests: List<TweetRequest>) {
        val request = requests.parseToRequestDb()
        requestDBService.saveRequests(request)
    }

    override suspend fun onRequestChanges(): Flow<List<RequestsDB>> {
        return requestDBService.getAllRequests().map { it.list }
    }

    override suspend fun initiateRequest(requestDb: RequestsDB): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = if (requestDb.requestType == TWEET_REQUEST) {
                    val tweets = requestDb.parseToTweetRequest()
                    if (tweets.isNotEmpty()) {
                        tweetRemoteService.uploadTweets(tweets)
                    } else {
                        null
                    }
                } else if (requestDb.requestType == FOLLOW_REQUEST) {
                    userFollowRemoteService.updateFollowStatus(requestDb.followingId)
                } else if (requestDb.requestType == BOOKMARK_REQUEST) {
                    userBookmarkRemoteService.updateBookmarkStatus(requestDb.tweetId)
                } else {
                    null
                }

                if (response != null) {
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
                }
            } catch (e: Exception) {
                emit(
                    ClientResponse.Error(
                        message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = DEFAULT_ERROR_CODE,
                    ),
                )
            }

            requestDBService.deleteRequest(requestDb.requestId)
            emit(ClientResponse.Idle)
        }

    override suspend fun saveFollowRequest(followingId: String) {
        requestDBService.saveRequests(
            RequestsDB().apply {
                this.requestType = FOLLOW_REQUEST
                this.followingId = followingId
            },
        )
    }

    override suspend fun saveBookmarkRequest(tweetId: String) {
        requestDBService.saveRequests(
            RequestsDB().apply {
                this.requestType = BOOKMARK_REQUEST
                this.tweetId = tweetId
            },
        )
    }
}