package data.device.tweet

import core.models.realm.TweetDB
import core.models.realm.TweetRequestsDB
import core.models.request.TweetRequest
import core.models.response.TweetResponse
import core.parser.parseToTweetRequestDb
import core.parser.parseToTweetsDB
import domain.services.tweet.TweetDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.SingleQueryChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId
import utils.Constants.DbKeys.CREATED_BY_CAMEL_CASE
import utils.Constants.DbKeys.GIF
import utils.Constants.DbKeys.IS_A_COMMENT_TWEET_CAMELCASE
import utils.Constants.DbKeys.IS_A_POLL_CAMEL_CASE
import utils.Constants.DbKeys.IS_A_QUOTE_TWEET_CAMEL_CASE
import utils.Constants.DbKeys.IS_LIKED_TWEET
import utils.Constants.DbKeys.IS_REPOST_TWEET_CAMEL_CASE
import utils.Constants.DbKeys.MEDIA
import utils.Constants.DbKeys.PARENT_TWEET_ID_CAMELCASE
import utils.Constants.DbKeys.REQUEST_ID
import utils.Constants.DbKeys.TWEETED_ON_TIMESTAMP
import utils.Constants.DbKeys.TWEET_ID
import utils.Constants.DbKeys.USER_ID

class TweetDBServiceImplementation(
    private val realm: Realm,
) : TweetDBService {
    override suspend fun saveAllTweets(tweetResponse: List<TweetResponse>): List<TweetDB> =
        realm.write {
            val unmanagedObject = tweetResponse.map { it.parseToTweetsDB() }
            val managedObject = unmanagedObject.map {
                copyToRealm(
                    it, updatePolicy = UpdatePolicy.ALL
                )
            }
            managedObject
        }

    override fun getAllTweets(): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>().sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override fun getAllTweetRequests(): Flow<ResultsChange<TweetRequestsDB>> {
        return realm.query<TweetRequestsDB>().asFlow()
    }

    override fun getTweetChanges(id: String): Flow<SingleQueryChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$TWEET_ID == $0", id
        ).first().asFlow()
    }

    override fun getAllTweetsReplyFor(tweetId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$PARENT_TWEET_ID_CAMELCASE == $0 AND $IS_A_COMMENT_TWEET_CAMELCASE == $1",
            tweetId,
            true,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override suspend fun deleteTweetRequest(id: String) {
        realm.write {
            query<TweetRequestsDB>("$REQUEST_ID == $0", ObjectId(id)).find().firstOrNull()
                ?.let { delete(it) }
        }
    }

    override fun getTweetById(id: String): TweetDB? {
        return realm.query<TweetDB>("$TWEET_ID == $0", id).find().firstOrNull()
    }

    override suspend fun deleteTweetById(id: String) {
        val tweet = getTweetById(id)
        realm.write {
            if (tweet != null) {
                findLatest(tweet)?.also { delete(it) }
            }
        }
    }

    override suspend fun saveTweetRequests(tweetRequest: List<TweetRequest>): TweetRequestsDB =
        realm.write {
            val unmanagedObject = tweetRequest.parseToTweetRequestDb()
            copyToRealm(unmanagedObject, updatePolicy = UpdatePolicy.ALL)
        }

    override suspend fun getAllUserPosts(userId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$CREATED_BY_CAMEL_CASE.$USER_ID == $0 AND ($IS_A_POLL_CAMEL_CASE == $1 OR $IS_A_QUOTE_TWEET_CAMEL_CASE == $2 OR $IS_REPOST_TWEET_CAMEL_CASE == $2)",
            userId,
            true,
            true,
            true,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override suspend fun getAllUserLikes(userId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$CREATED_BY_CAMEL_CASE.$USER_ID == $0 AND $IS_LIKED_TWEET == $1",
            userId,
            true,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override suspend fun getAllUserReplies(userId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$CREATED_BY_CAMEL_CASE.$USER_ID == $0 AND $IS_A_COMMENT_TWEET_CAMELCASE == $0",
            userId,
            true,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override suspend fun getAllUserMedia(userId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$CREATED_BY_CAMEL_CASE.$USER_ID == $0 AND ($MEDIA.@size > $1 OR $GIF.@size > $2 )",
            userId,
            0,
            1,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }
}