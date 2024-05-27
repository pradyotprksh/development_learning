package domain.repositories.tweet

import core.models.realm.TweetDB
import core.models.response.ClientResponse
import data.response.XFullStackResponse
import domain.services.tweet.TweetDBService
import domain.services.tweet.TweetRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Localization
import utils.XFullStackResponseStatus

class TweetRepositoryImplementation(
    private val tweetDBService: TweetDBService,
    private val tweetRemoteService: TweetRemoteService,
) : TweetRepository {
    override suspend fun getAllTweets(): Flow<ClientResponse<out XFullStackResponse<List<TweetDB>>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = tweetRemoteService.getAllTweets()
                if (response.status == XFullStackResponseStatus.Success) {
                    response.data?.let { tweetsResponse ->
                        val dbSavedData = tweetDBService.saveAllTweets(tweetsResponse)
                        emit(
                            ClientResponse.Success(
                                XFullStackResponse(
                                    status = response.status,
                                    message = response.message,
                                    code = response.code,
                                    data = dbSavedData,
                                )
                            )
                        )
                    }
                } else {
                    val dbSavedData = tweetDBService.getAllTweets()
                    emit(
                        ClientResponse.Success(
                            XFullStackResponse(
                                status = XFullStackResponseStatus.Success,
                                message = null,
                                code = null,
                                data = dbSavedData,
                            )
                        )
                    )
                }
            } catch (mainE: Exception) {
                try {
                    val dbSavedData = tweetDBService.getAllTweets()
                    emit(
                        ClientResponse.Success(
                            XFullStackResponse(
                                status = XFullStackResponseStatus.Success,
                                message = null,
                                code = null,
                                data = dbSavedData,
                            )
                        )
                    )
                } catch (e: Exception) {
                    emit(
                        ClientResponse.Error(
                            message = mainE.message ?: e.message
                            ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = DEFAULT_ERROR_CODE,
                        ),
                    )
                }
            }
            emit(ClientResponse.Idle)
        }
}