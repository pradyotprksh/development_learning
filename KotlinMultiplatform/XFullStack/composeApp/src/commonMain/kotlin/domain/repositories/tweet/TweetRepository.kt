package domain.repositories.tweet

import core.models.realm.TweetDB
import core.models.response.ClientResponse
import data.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface TweetRepository {
    suspend fun updateAllTweets(
        page: Int = 1,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun allTweetsChanges(): Flow<List<TweetDB>>

    suspend fun updateTweetPoll(
        tweetId: String,
        pollId: String,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>
}