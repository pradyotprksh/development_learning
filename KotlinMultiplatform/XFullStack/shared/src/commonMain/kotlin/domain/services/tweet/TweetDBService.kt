package domain.services.tweet

import core.models.realm.TweetDB
import core.models.realm.TweetRequestsDB
import core.models.request.TweetRequest
import core.models.response.TweetResponse
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.SingleQueryChange
import kotlinx.coroutines.flow.Flow

interface TweetDBService {
    suspend fun saveAllTweets(tweetResponse: List<TweetResponse>): List<TweetDB>

    fun getAllTweets(): Flow<ResultsChange<TweetDB>>

    fun getAllTweetRequests(): Flow<ResultsChange<TweetRequestsDB>>

    fun getTweetChanges(id: String): Flow<SingleQueryChange<TweetDB>>

    fun getAllTweetsReplyFor(tweetId: String): Flow<ResultsChange<TweetDB>>

    suspend fun deleteTweetRequest(id: String)

    fun getTweetById(id: String): TweetDB?

    suspend fun deleteTweetById(id: String)

    suspend fun saveTweetRequests(tweetRequest: List<TweetRequest>): TweetRequestsDB

    suspend fun getAllUserPosts(userId: String): Flow<ResultsChange<TweetDB>>

    suspend fun getAllUserLikes(userId: String): Flow<ResultsChange<TweetDB>>

    suspend fun getAllUserReplies(userId: String): Flow<ResultsChange<TweetDB>>

    suspend fun getAllUserMedia(userId: String): Flow<ResultsChange<TweetDB>>
}