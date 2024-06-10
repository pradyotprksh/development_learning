package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TokenDB : RealmObject {
    @PrimaryKey
    var userId: String = ""
    var token: String = ""
}
