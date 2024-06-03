package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TweetRequestDB : RealmObject {
    @PrimaryKey
    var requestId: ObjectId = ObjectId()
    var tweet: String? = null
    var media: RealmList<String> = realmListOf()
    var gif: RealmList<String> = realmListOf()
    var isAPoll: Boolean = false
    var pollChoices: RealmList<String> = realmListOf()
    var pollHour: Long? = null
    var pollMinute: Long? = null
    var pollSeconds: Long? = null
    var isALocation: Boolean = false
    var location: String? = null
    var isScheduledTweet: Boolean = false
    var scheduledOnTweet: Long? = null
    var isQuoteTweet: Boolean = false
    var parentTweetId: String? = null
}