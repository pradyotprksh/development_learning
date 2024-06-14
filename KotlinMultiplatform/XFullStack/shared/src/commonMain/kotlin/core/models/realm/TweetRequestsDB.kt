package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.REQUEST_ID

class TweetRequestsDB : RealmObject {
    @PrimaryKey
    @PersistedName(REQUEST_ID)
    var requestId: String = ""
    var tweets: RealmList<TweetRequestDB> = realmListOf()
}