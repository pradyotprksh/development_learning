package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CurrentUserIdDB : RealmObject {
    @PrimaryKey
    var userId: String = ""
}
