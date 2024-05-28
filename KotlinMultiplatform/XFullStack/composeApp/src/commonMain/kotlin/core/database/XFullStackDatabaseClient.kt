package core.database

import core.models.realm.CurrentUserIdDB
import core.models.realm.CurrentUserInfoDB
import core.models.realm.PollChoicesDB
import core.models.realm.TokenDB
import core.models.realm.TweetDB
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.log.RealmLog

object XFullStackDatabaseClient {
    fun createDatabaseClient(): Realm {
        val config =
            RealmConfiguration.create(
                schema = setOf(
                    TokenDB::class,
                    CurrentUserIdDB::class,
                    CurrentUserInfoDB::class,
                    PollChoicesDB::class,
                    TweetDB::class,
                )
            )
        RealmLog.level = LogLevel.TRACE
        return Realm.open(config)
    }
}