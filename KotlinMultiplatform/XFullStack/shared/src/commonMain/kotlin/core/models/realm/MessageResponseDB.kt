package core.models.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.CHAT_ID
import utils.Constants.DbKeys.FORWARD_MESSAGE_DETAILS
import utils.Constants.DbKeys.ID
import utils.Constants.DbKeys.IS_READ
import utils.Constants.DbKeys.IS_SEND_BY_CURRENT_USER
import utils.Constants.DbKeys.MESSAGE_BY
import utils.Constants.DbKeys.MESSAGE_GROUP
import utils.Constants.DbKeys.MESSAGE_ON
import utils.Constants.DbKeys.MESSAGE_TIME
import utils.Constants.DbKeys.MESSAGE_TIME_AGO
import utils.Constants.DbKeys.NOTIFICATION_MESSAGE
import utils.Constants.DbKeys.REPLY_MESSAGE_DETAILS
import utils.Constants.DbKeys.TWEET_DETAILS

class MessageResponseDB : RealmObject {
    @PrimaryKey
    @PersistedName(ID)
    var id: String = ""

    @PersistedName(CHAT_ID)
    var chatId: String = ""
    var message: String = ""

    @PersistedName(MESSAGE_ON)
    var messageOn: Long = 0

    @PersistedName(MESSAGE_TIME)
    var messageTime: String = ""

    @PersistedName(MESSAGE_TIME_AGO)
    var messageTimeAgo: String = ""

    @PersistedName(MESSAGE_BY)
    var messageBy: UserInfoDB? = null

    @PersistedName(MESSAGE_GROUP)
    var messageGroup: String = ""

    @PersistedName(IS_READ)
    var isRead: Boolean = false
    var media: RealmList<String> = realmListOf()
    var gif: RealmList<String> = realmListOf()

    @PersistedName(REPLY_MESSAGE_DETAILS)
    var replyMessageDetails: MessageResponseDB? = null

    @PersistedName(NOTIFICATION_MESSAGE)
    var notificationMessage: Boolean = false

    @PersistedName(FORWARD_MESSAGE_DETAILS)
    var forwardMessageDetails: MessageResponseDB? = null
    var reaction: RealmList<String> = realmListOf()
    var audio: String = ""

    @PersistedName(TWEET_DETAILS)
    var tweetDetails: TweetDB? = null

    @PersistedName(IS_SEND_BY_CURRENT_USER)
    var isSendByCurrentUser: Boolean = false
}