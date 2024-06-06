package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ViewDB : RealmObject {
    @PrimaryKey
    var viewedId: String = ""
    var isUpdatedOnline: Boolean = false
}