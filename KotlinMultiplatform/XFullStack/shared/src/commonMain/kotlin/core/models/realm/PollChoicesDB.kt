package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.VOTE_COUNT

class PollChoicesDB : RealmObject {
    @PrimaryKey
    var id: String = ""
    var choice: String = ""

    @PersistedName(VOTE_COUNT)
    var voteCount: Int = 0
}