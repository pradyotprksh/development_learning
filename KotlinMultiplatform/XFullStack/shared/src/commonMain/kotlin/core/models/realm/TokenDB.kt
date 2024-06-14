package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.USER_ID

class TokenDB : RealmObject {
    @PrimaryKey
    @PersistedName(USER_ID)
    var userId: String = ""
    var token: String = ""
}
