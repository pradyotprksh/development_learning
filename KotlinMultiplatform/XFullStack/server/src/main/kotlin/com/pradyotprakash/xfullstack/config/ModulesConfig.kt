package com.pradyotprakash.xfullstack.config

import com.pradyotprakash.xfullstack.core.database.XFullStackMongoDBClient
import com.pradyotprakash.xfullstack.data.user.MongoUserDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object ModulesConfig {
    private val databaseModule = DI.Module("DATABASE") {
        bindSingleton { XFullStackMongoDBClient.getDatabase() }
        bindProvider<UserDataSource> { MongoUserDataSource(instance()) }
    }

    val di = DI {
        importAll(
            databaseModule,
        )
    }
}