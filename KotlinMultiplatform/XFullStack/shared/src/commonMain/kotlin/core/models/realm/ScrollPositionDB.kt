package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ScrollPositionDB : RealmObject {
    @PrimaryKey
    var id: String = ""
    var postion: Int = 0
}