package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.IS_UPDATED_ONLINE
import utils.Constants.DbKeys.VIEWED_ID

class ViewDB : RealmObject {
    @PrimaryKey
    @PersistedName(VIEWED_ID)
    var viewedId: String = ""

    @PersistedName(IS_UPDATED_ONLINE)
    var isUpdatedOnline: Boolean = false
}