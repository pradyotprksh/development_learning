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
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId
import utils.Constants.DbKeys.REQUEST_ID
import utils.Constants.DbKeys.TWEETED_ON_TIMESTAMP
import utils.Constants.DbKeys.TWEET_ID

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
}