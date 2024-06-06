package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class TweetViewDB : RealmObject {
    @PrimaryKey
    var tweetId: ObjectId = BsonObjectId()
    var viewedOn: Long = 0
    var isUpdatedOnline: Boolean = false
}