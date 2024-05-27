package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TweetDB : RealmObject {
    @PrimaryKey
    var tweetId: String = ""
    var tweet: String = ""
    var createdBy: CurrentUserInfoDB = CurrentUserInfoDB()
    var tweetedOn: Long = 0
    var media: List<String> = emptyList()
    var gif: List<String> = emptyList()
    var commentCount: Int = 0
    var retweetCount: Int = 0
    var likeCount: Int = 0
    var views: Int = 0
    var isAPoll: Boolean = false
    var pollChoices: List<PollChoicesDB> = emptyList()
    var isPollingAllowed: Boolean = false
    var location: String = ""
    var isACommentTweet: Boolean = false
    var isQuoteTweet: Boolean = false
    var isRepostTweet: Boolean = false
    var isLikedTweet: Boolean = false
    var parentTweetId: String? = null
}