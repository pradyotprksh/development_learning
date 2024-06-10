package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PollChoicesDB : RealmObject {
    @PrimaryKey
    var id: String = ""
    var choice: String = ""
    var voteCount: Int = 0
}