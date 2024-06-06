package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class TweetRequestDB : EmbeddedRealmObject {
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
    var isLikedTweet: Boolean = false
    var isRepostTweet: Boolean = false
    var isACommentTweet: Boolean = false
    var parentTweetId: String? = null
}