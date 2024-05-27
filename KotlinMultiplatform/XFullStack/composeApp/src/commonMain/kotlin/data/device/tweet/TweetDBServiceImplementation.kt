package data.device.tweet

import data.response.TweetsResponse
import domain.services.tweet.TweetDBService
import io.realm.kotlin.Realm

class TweetDBServiceImplementation(
    private val realm: Realm,
) : TweetDBService {
    override suspend fun saveAllTweets(tweetsResponse: TweetsResponse) {
        TODO("Not yet implemented")
    }
}