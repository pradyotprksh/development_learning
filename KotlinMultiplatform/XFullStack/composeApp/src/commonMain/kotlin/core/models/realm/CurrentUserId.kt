package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CurrentUserId : RealmObject {
    @PrimaryKey
    var userId: String = ""
}
