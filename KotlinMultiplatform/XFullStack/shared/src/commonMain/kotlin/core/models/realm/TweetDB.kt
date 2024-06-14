package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.BOOKMARK_COUNT
import utils.Constants.DbKeys.COMMENT_COUNT
import utils.Constants.DbKeys.CREATED_BY
import utils.Constants.DbKeys.GIF
import utils.Constants.DbKeys.IS_A_COMMENT_TWEET
import utils.Constants.DbKeys.IS_A_LIKED_TWEET
import utils.Constants.DbKeys.IS_A_POLL
import utils.Constants.DbKeys.IS_A_QUOTE_TWEET
import utils.Constants.DbKeys.IS_A_REPOST_TWEET
import utils.Constants.DbKeys.IS_LIKED_BY_CURRENT_USER
import utils.Constants.DbKeys.IS_POLLING_ALLOWED
import utils.Constants.DbKeys.LIKE_COUNT
import utils.Constants.DbKeys.MEDIA
import utils.Constants.DbKeys.PARENT_TWEET_DETAILS
import utils.Constants.DbKeys.PARENT_TWEET_DETAILS_NOT_FOUND
import utils.Constants.DbKeys.PARENT_TWEET_ID
import utils.Constants.DbKeys.POLLING_END_TIME
import utils.Constants.DbKeys.POLL_CHOICES
import utils.Constants.DbKeys.QUOTES_COUNT
import utils.Constants.DbKeys.REPOST_COUNT
import utils.Constants.DbKeys.SCHEDULED_ON_TWEET
import utils.Constants.DbKeys.TWEETED_ON
import utils.Constants.DbKeys.TWEETED_ON_TIMESTAMP
import utils.Constants.DbKeys.TWEET_ID

class TweetDB : RealmObject {
    @PrimaryKey
    @PersistedName(TWEET_ID)
    var tweetId: String = ""
    var tweet: String = ""

    @PersistedName(CREATED_BY)
    var createdBy: UserInfoDB? = null

    @PersistedName(TWEETED_ON_TIMESTAMP)
    var tweetedOnTimestamp: Long = 0

    @PersistedName(TWEETED_ON)
    var tweetedOn: String = ""

    @PersistedName(MEDIA)
    var media: RealmList<String> = realmListOf()

    @PersistedName(GIF)
    var gif: RealmList<String> = realmListOf()

    @PersistedName(COMMENT_COUNT)
    var commentCount: Int = 0

    @PersistedName(LIKE_COUNT)
    var likeCount: Int = 0
    var views: Int = 0

    @PersistedName(REPOST_COUNT)
    var repostsCount: Int = 0

    @PersistedName(QUOTES_COUNT)
    var quotesCount: Int = 0

    @PersistedName(BOOKMARK_COUNT)
    var bookmarkCount: Int = 0

    @PersistedName(IS_A_POLL)
    var isAPoll: Boolean = false

    @PersistedName(POLL_CHOICES)
    var pollChoices: RealmList<PollChoicesDB> = realmListOf()

    @PersistedName(POLLING_END_TIME)
    var pollingEndTime: String = ""

    @PersistedName(IS_POLLING_ALLOWED)
    var isPollingAllowed: Boolean = false
    var location: String = ""

    @PersistedName(IS_A_COMMENT_TWEET)
    var isACommentTweet: Boolean = false

    @PersistedName(IS_A_QUOTE_TWEET)
    var isQuoteTweet: Boolean = false

    @PersistedName(IS_A_REPOST_TWEET)
    var isRepostTweet: Boolean = false

    @PersistedName(IS_A_LIKED_TWEET)
    var isLikedTweet: Boolean = false

    @PersistedName(IS_LIKED_BY_CURRENT_USER)
    var isLikedByCurrentUser: Boolean = false

    @PersistedName(PARENT_TWEET_ID)
    var parentTweetId: String? = null

    @PersistedName(PARENT_TWEET_DETAILS)
    var parentTweetDetails: TweetDB? = null

    @PersistedName(SCHEDULED_ON_TWEET)
    var scheduledOnTweet: Long = 0

    @PersistedName(PARENT_TWEET_DETAILS_NOT_FOUND)
    var parentTweetDetailsNotFound: Boolean = false
}