package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.COUNT
import utils.Constants.DbKeys.ID
import utils.Constants.DbKeys.IS_TRENDING
import utils.Constants.DbKeys.TOTAL_TWEETS_COUNT

class TagsDB : RealmObject {
    @PrimaryKey
    @PersistedName(ID)
    var id: String = ""

    @PersistedName(COUNT)
    var count: Int = 0

    @PersistedName(TOTAL_TWEETS_COUNT)
    var totalTweets: Int = 0

    @PersistedName(IS_TRENDING)
    var isTrending: Boolean = false
}