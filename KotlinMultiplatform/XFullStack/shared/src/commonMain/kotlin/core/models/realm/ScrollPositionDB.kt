package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.ID

class ScrollPositionDB : RealmObject {
    @PrimaryKey
    @PersistedName(ID)
    var id: String = ""
    var postion: Int = 0
}