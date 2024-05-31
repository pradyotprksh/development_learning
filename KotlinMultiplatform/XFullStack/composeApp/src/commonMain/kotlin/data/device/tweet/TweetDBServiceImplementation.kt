package data.device.tweet

import core.models.realm.TweetDB
import core.parser.parseToTweetsDB
import data.response.TweetsResponse
import domain.services.tweet.TweetDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.TWEETED_ON_TIMESTAMP
import utils.Constants.DbKeys.TWEET_ID

class TweetDBServiceImplementation(
    private val realm: Realm,
) : TweetDBService {
    override suspend fun saveAllTweets(tweetsResponse: List<TweetsResponse>): List<TweetDB> =
        realm.write {
            val unmanagedObject = tweetsResponse.map { it.parseToTweetsDB() }
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

    override fun getTweetById(id: String): TweetDB? {
        return realm.query<TweetDB>("$TWEET_ID == $0", id).find().firstOrNull()
    }
}