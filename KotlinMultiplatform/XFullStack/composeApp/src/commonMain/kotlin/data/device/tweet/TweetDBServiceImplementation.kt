package data.device.tweet

import core.models.realm.TweetDB
import core.parser.parseToTweetsDB
import data.response.TweetsResponse
import domain.services.tweet.TweetDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query

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

    override suspend fun getAllTweets(): List<TweetDB> {
        return realm.query<TweetDB>().find()
    }
}