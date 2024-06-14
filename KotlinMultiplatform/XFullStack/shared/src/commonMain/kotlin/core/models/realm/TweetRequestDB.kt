package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.annotations.PersistedName
import utils.Constants.DbKeys.GIF
import utils.Constants.DbKeys.IS_A_COMMENT_TWEET
import utils.Constants.DbKeys.IS_A_LIKED_TWEET
import utils.Constants.DbKeys.IS_A_LOCATION
import utils.Constants.DbKeys.IS_A_POLL
import utils.Constants.DbKeys.IS_A_QUOTE_TWEET
import utils.Constants.DbKeys.IS_A_REPOST_TWEET
import utils.Constants.DbKeys.IS_SCHEDULED_TWEET
import utils.Constants.DbKeys.MEDIA
import utils.Constants.DbKeys.PARENT_TWEET_ID
import utils.Constants.DbKeys.POLL_CHOICES
import utils.Constants.DbKeys.POLL_HOUR
import utils.Constants.DbKeys.POLL_MINUTE
import utils.Constants.DbKeys.POLL_SECONDS
import utils.Constants.DbKeys.SCHEDULED_ON_TWEET

class TweetRequestDB : EmbeddedRealmObject {
    var tweet: String? = null

    @PersistedName(MEDIA)
    var media: RealmList<String> = realmListOf()

    @PersistedName(GIF)
    var gif: RealmList<String> = realmListOf()

    @PersistedName(IS_A_POLL)
    var isAPoll: Boolean = false

    @PersistedName(POLL_CHOICES)
    var pollChoices: RealmList<String> = realmListOf()

    @PersistedName(POLL_HOUR)
    var pollHour: Long? = null

    @PersistedName(POLL_MINUTE)
    var pollMinute: Long? = null

    @PersistedName(POLL_SECONDS)
    var pollSeconds: Long? = null

    @PersistedName(IS_A_LOCATION)
    var isALocation: Boolean = false
    var location: String? = null

    @PersistedName(IS_SCHEDULED_TWEET)
    var isScheduledTweet: Boolean = false

    @PersistedName(SCHEDULED_ON_TWEET)
    var scheduledOnTweet: Long? = null

    @PersistedName(IS_A_QUOTE_TWEET)
    var isQuoteTweet: Boolean = false

    @PersistedName(IS_A_LIKED_TWEET)
    var isLikedTweet: Boolean = false

    @PersistedName(IS_A_REPOST_TWEET)
    var isRepostTweet: Boolean = false

    @PersistedName(IS_A_COMMENT_TWEET)
    var isACommentTweet: Boolean = false

    @PersistedName(PARENT_TWEET_ID)
    var parentTweetId: String? = null
}