package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.annotations.PersistedName
import utils.Constants.DbKeys.CHAT_ID
import utils.Constants.DbKeys.ID
import utils.Constants.DbKeys.MESSAGE_ON

class GrokMessageDB : EmbeddedRealmObject {
    @PersistedName(ID)
    var id: String = ""

    @PersistedName(CHAT_ID)
    var chatId: String = ""

    var messages: RealmList<String> = realmListOf()

    @PersistedName(MESSAGE_ON)
    var messageOn: Long = 0

    var role: String = ""
}