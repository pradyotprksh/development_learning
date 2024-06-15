package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.FOLLOWING_ID
import utils.Constants.DbKeys.REQUEST_ID
import utils.Constants.DbKeys.REQUEST_TYPE
import utils.Constants.DbKeys.TWEET_ID

class RequestsDB : RealmObject {
    @PrimaryKey
    @PersistedName(REQUEST_ID)
    var requestId: String = ""

    @PersistedName(REQUEST_TYPE)
    var requestType: String = ""

    @PersistedName(TWEET_ID)
    var tweetId: String = ""

    @PersistedName(FOLLOWING_ID)
    var followingId: String = ""

    var tweets: RealmList<TweetRequestDB> = realmListOf()
}