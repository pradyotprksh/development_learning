package core.database

import core.models.realm.ChatResponseDB
import core.models.realm.CurrentUserIdDB
import core.models.realm.CurrentUserInfoDB
import core.models.realm.GrokChatDB
import core.models.realm.GrokMessageDB
import core.models.realm.MessageRequestDB
import core.models.realm.MessageResponseDB
import core.models.realm.PollChoicesDB
import core.models.realm.RequestsDB
import core.models.realm.ScrollPositionDB
import core.models.realm.TagsDB
import core.models.realm.TokenDB
import core.models.realm.TweetDB
import core.models.realm.TweetRequestDB
import core.models.realm.UserInfoDB
import core.models.realm.ViewDB
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object XFullStackDatabaseClient {
    fun createDatabaseClient(): Realm {
        val config =
            RealmConfiguration.create(
                schema = setOf(
                    TokenDB::class,
                    CurrentUserIdDB::class,
                    CurrentUserInfoDB::class,
                    UserInfoDB::class,
                    PollChoicesDB::class,
                    TweetDB::class,
                    RequestsDB::class,
                    TweetRequestDB::class,
                    ViewDB::class,
                    ScrollPositionDB::class,
                    TagsDB::class,
                    MessageRequestDB::class,
                    MessageResponseDB::class,
                    ChatResponseDB::class,
                    GrokChatDB::class,
                    GrokMessageDB::class,
                )
            )
        return Realm.open(config)
    }
}