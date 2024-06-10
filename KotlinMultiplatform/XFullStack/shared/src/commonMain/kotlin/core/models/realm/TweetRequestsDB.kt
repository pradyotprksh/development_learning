package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TweetRequestsDB : RealmObject {
    @PrimaryKey
    var requestId: ObjectId = ObjectId()
    var tweets: RealmList<TweetRequestDB> = realmListOf()
}