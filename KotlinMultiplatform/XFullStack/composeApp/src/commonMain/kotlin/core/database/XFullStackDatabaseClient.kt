package core.database

import core.models.realm.CurrentUserId
import core.models.realm.Token
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.log.RealmLog

object XFullStackDatabaseClient {
    fun createDatabaseClient(): Realm {
        val config = RealmConfiguration.create(schema = setOf(Token::class, CurrentUserId::class))
        RealmLog.level = LogLevel.TRACE
        return Realm.open(config)
    }
}