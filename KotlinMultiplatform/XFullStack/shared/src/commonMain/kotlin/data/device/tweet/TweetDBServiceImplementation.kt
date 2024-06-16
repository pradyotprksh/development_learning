package data.device.tweet

import core.models.realm.TweetDB
import core.models.response.TweetResponse
import core.parser.parseToTweetsDB
import domain.services.tweet.TweetDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.SingleQueryChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.CREATED_BY
import utils.Constants.DbKeys.GIF
import utils.Constants.DbKeys.IS_A_COMMENT_TWEET
import utils.Constants.DbKeys.IS_A_LIKED_TWEET
import utils.Constants.DbKeys.IS_A_POLL
import utils.Constants.DbKeys.IS_A_QUOTE_TWEET
import utils.Constants.DbKeys.IS_A_REPOST_TWEET
import utils.Constants.DbKeys.IS_BOOKMARKED_BY_CURRENT_USER
import utils.Constants.DbKeys.IS_FOLLOWING_CURRENT_USER
import utils.Constants.DbKeys.MEDIA
import utils.Constants.DbKeys.PARENT_TWEET_ID
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

    override fun allFollowingTweetsChanges(userId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$CREATED_BY.$IS_FOLLOWING_CURRENT_USER == $0", true,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override fun allBookmarksTweetsChanges(userId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$IS_BOOKMARKED_BY_CURRENT_USER == $0", true,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override fun getTweetChanges(id: String): Flow<SingleQueryChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$TWEET_ID == $0", id
        ).first().asFlow()
    }

    override fun getAllTweetsReplyFor(tweetId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$PARENT_TWEET_ID == $0 AND $IS_A_COMMENT_TWEET == $1",
            tweetId,
            true,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
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

    override suspend fun getAllUserPosts(userId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$CREATED_BY.$USER_ID == $0 AND ($IS_A_POLL == $1 OR $IS_A_QUOTE_TWEET == $2 OR $IS_A_REPOST_TWEET == $3)",
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
            "$CREATED_BY.$USER_ID == $0 AND $IS_A_LIKED_TWEET == $1",
            userId,
            true,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override suspend fun getAllUserReplies(userId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$CREATED_BY.$USER_ID == $0 AND $IS_A_COMMENT_TWEET == $1",
            userId,
            true,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }

    override suspend fun getAllUserMedia(userId: String): Flow<ResultsChange<TweetDB>> {
        return realm.query<TweetDB>(
            "$CREATED_BY.$USER_ID == $0 AND ($MEDIA.@size > $1 OR $GIF.@size > $2)",
            userId,
            0,
            1,
        ).sort(
            property = TWEETED_ON_TIMESTAMP,
            sortOrder = Sort.DESCENDING,
        ).asFlow()
    }
}