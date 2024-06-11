package core.database

import core.models.realm.CurrentUserIdDB
import core.models.realm.CurrentUserInfoDB
import core.models.realm.PollChoicesDB
import core.models.realm.ScrollPositionDB
import core.models.realm.TokenDB
import core.models.realm.TweetDB
import core.models.realm.TweetRequestDB
import core.models.realm.TweetRequestsDB
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
                    PollChoicesDB::class,
                    TweetDB::class,
                    TweetRequestsDB::class,
                    TweetRequestDB::class,
                    ViewDB::class,
                    ScrollPositionDB::class,
                )
            )
        return Realm.open(config)
    }
}