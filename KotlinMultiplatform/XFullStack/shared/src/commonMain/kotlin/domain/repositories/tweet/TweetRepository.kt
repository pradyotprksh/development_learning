package domain.repositories.tweet

import core.models.response.ClientResponse
import core.models.response.TweetResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow
import utils.Constants.ConstValues.DEFAULT_PAGINATE_LIMIT

interface TweetRepository {
    suspend fun updateAllTweets(
        page: Int = 1,
        limit: Int = DEFAULT_PAGINATE_LIMIT,
    ): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun allTweetsChanges(): Flow<List<TweetResponse>>

    suspend fun allFollowingTweetsChanges(userId: String): Flow<List<TweetResponse>>

    suspend fun allBookmarksTweetsChanges(userId: String): Flow<List<TweetResponse>>

    suspend fun getTweetChanges(id: String): Flow<TweetResponse?>

    suspend fun getAllTweetsReplyFor(id: String): Flow<List<TweetResponse>>

    suspend fun getTweetDetails(
        tweetId: String,
    ): TweetResponse?

    suspend fun deleteTweetById(id: String)

    suspend fun getAllUserPosts(userId: String): Flow<List<TweetResponse>>

    suspend fun getAllUserLikes(userId: String): Flow<List<TweetResponse>>

    suspend fun getAllUserReplies(userId: String): Flow<List<TweetResponse>>

    suspend fun getAllUserMedia(userId: String): Flow<List<TweetResponse>>
}