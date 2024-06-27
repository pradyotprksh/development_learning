package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.CHAT_ID
import utils.Constants.DbKeys.CREATED_BY
import utils.Constants.DbKeys.CREATED_ON
import utils.Constants.DbKeys.IS_DIRECT
import utils.Constants.DbKeys.IS_GROUP
import utils.Constants.DbKeys.LAST_MESSAGE_DETAILS

class ChatResponseDB : RealmObject {
    @PrimaryKey
    @PersistedName(CHAT_ID)
    var chatId: String = ""
    var users: RealmList<UserInfoDB> = realmListOf()

    @PersistedName(CREATED_ON)
    var createdOn: Long = 0

    @PersistedName(CREATED_BY)
    var createdBy: String = ""

    @PersistedName(IS_GROUP)
    var isGroup: Boolean = false

    @PersistedName(IS_DIRECT)
    var isDirect: Boolean = false

    @PersistedName(LAST_MESSAGE_DETAILS)
    var lastMessageDetails: MessageResponseDB? = null
}