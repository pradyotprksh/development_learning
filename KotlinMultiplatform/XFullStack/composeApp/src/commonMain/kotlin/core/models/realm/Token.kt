package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Token : RealmObject {
    @PrimaryKey
    var userId: String = ""
    var token: String = ""
}
