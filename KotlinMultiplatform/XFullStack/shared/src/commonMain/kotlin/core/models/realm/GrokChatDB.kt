package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.CHAT_ID
import utils.Constants.DbKeys.CHAT_TITLE
import utils.Constants.DbKeys.CREATED_ON
import utils.Constants.DbKeys.IS_ARCHIVED
import utils.Constants.DbKeys.LAST_MESSAGE_ON
import utils.Constants.DbKeys.MESSAGES

class GrokChatDB : RealmObject {
    @PrimaryKey
    @PersistedName(CHAT_ID)
    var chatId = ""

    @PersistedName(CHAT_TITLE)
    var chatTitle = ""

    @PersistedName(IS_ARCHIVED)
    var isArchived = false

    @PersistedName(CREATED_ON)
    var createdOn: Long = 0

    @PersistedName(LAST_MESSAGE_ON)
    var lastMessageOn: Long = 0

    @PersistedName(MESSAGES)
    var messages: RealmList<GrokMessageDB> = realmListOf()
}