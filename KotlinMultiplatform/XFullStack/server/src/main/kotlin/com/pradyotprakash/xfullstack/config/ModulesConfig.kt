package com.pradyotprakash.xfullstack.config

import com.pradyotprakash.xfullstack.core.database.XFullStackMongoDBClient
import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.hashing.SHA256HashingService
import com.pradyotprakash.xfullstack.core.security.token.JwtTokenService
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.user.MongoUserDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.controllers.AuthenticationController
import com.pradyotprakash.xfullstack.features.authentication.controllers.register.RegisterController
import com.pradyotprakash.xfullstack.features.authentication.controllers.register.RegisterControllerImplementation
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object ModulesConfig {
    private val databaseModule = DI.Module("DATABASE") {
        bindSingleton { XFullStackMongoDBClient.getDatabase() }
        bindProvider<UserDataSource> { MongoUserDataSource(instance()) }
    }

    private val securityModule = DI.Module("SECURITY") {
        bindProvider<TokenService> { JwtTokenService() }
        bindProvider<HashingService> { SHA256HashingService() }
    }

    private val controllersModule = DI.Module("CONTROLLERS") {
        bindProvider<RegisterController> { RegisterControllerImplementation() }
    }

    private val featuresModule = DI.Module("FEATURES") {
        bindProvider { AuthenticationController(instance()) }
    }

    val di = DI {
        importAll(
            databaseModule,
            securityModule,
            controllersModule,
            featuresModule,
        )
    }
}