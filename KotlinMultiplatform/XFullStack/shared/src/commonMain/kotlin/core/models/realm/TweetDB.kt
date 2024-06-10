package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TweetDB : RealmObject {
    @PrimaryKey
    var tweetId: String = ""
    var tweet: String = ""
    var createdBy: CurrentUserInfoDB? = null
    var tweetedOnTimestamp: Long = 0
    var tweetedOn: String = ""
    var media: RealmList<String> = realmListOf()
    var gif: RealmList<String> = realmListOf()
    var commentCount: Int = 0
    var retweetCount: Int = 0
    var likeCount: Int = 0
    var views: Int = 0
    var isAPoll: Boolean = false
    var pollChoices: RealmList<PollChoicesDB> = realmListOf()
    var pollingEndTime: String = ""
    var isPollingAllowed: Boolean = false
    var location: String = ""
    var isACommentTweet: Boolean = false
    var isQuoteTweet: Boolean = false
    var isRepostTweet: Boolean = false
    var isLikedTweet: Boolean = false
    var isLikedByCurrentUser: Boolean = false
    var parentTweetId: String? = null
    var parentTweetDetails: TweetDB? = null
    var scheduledOnTweet: Long = 0
    var parentTweetDetailsNotFound: Boolean = false
}