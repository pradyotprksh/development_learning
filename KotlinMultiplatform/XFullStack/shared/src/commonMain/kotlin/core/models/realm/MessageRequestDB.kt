package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.annotations.PersistedName
import utils.Constants.DbKeys.CHAT_ID
import utils.Constants.DbKeys.FORWARD_MESSAGE_ID
import utils.Constants.DbKeys.MESSAGE_TO
import utils.Constants.DbKeys.REPLY_MESSAGE_ID
import utils.Constants.DbKeys.TWEET_ID

class MessageRequestDB : EmbeddedRealmObject {
    @PersistedName(CHAT_ID)
    var chatId: String = ""
    var message: String = ""

    @PersistedName(MESSAGE_TO)
    var messageTo: String = ""
    var users: RealmList<String> = realmListOf()
    var media: RealmList<String> = realmListOf()
    var gif: RealmList<String> = realmListOf()

    @PersistedName(REPLY_MESSAGE_ID)
    var replyMessageId: String = ""

    @PersistedName(FORWARD_MESSAGE_ID)
    var forwardMessageId: String = ""
    var reaction: String = ""
    var audio: String = ""

    @PersistedName(TWEET_ID)
    var tweetId: String = ""
}