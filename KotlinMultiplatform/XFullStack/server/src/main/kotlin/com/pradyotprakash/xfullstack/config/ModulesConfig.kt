package com.pradyotprakash.xfullstack.config

import com.pradyotprakash.xfullstack.core.database.XFullStackMongoDBClient
import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.core.security.hashing.SHA256HashingService
import com.pradyotprakash.xfullstack.core.security.token.JwtTokenService
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenService
import com.pradyotprakash.xfullstack.data.user.MongoUserDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.controllers.AuthenticationController
import com.pradyotprakash.xfullstack.features.authentication.controllers.authenticate.AuthenticateController
import com.pradyotprakash.xfullstack.features.authentication.controllers.authenticate.AuthenticateControllerImplementation
import com.pradyotprakash.xfullstack.features.authentication.controllers.login.LoginController
import com.pradyotprakash.xfullstack.features.authentication.controllers.login.LoginControllerImplementation
import com.pradyotprakash.xfullstack.features.authentication.controllers.register.RegisterController
import com.pradyotprakash.xfullstack.features.authentication.controllers.register.RegisterControllerImplementation
import com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo.UserInfoController
import com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo.UserInfoControllerImplementation
import com.pradyotprakash.xfullstack.features.utils.controllers.UtilsController
import com.pradyotprakash.xfullstack.features.utils.controllers.usernameValid.UsernameValidController
import com.pradyotprakash.xfullstack.features.utils.controllers.usernameValid.UsernameValidControllerImplementation
import com.pradyotprakash.xfullstack.utils.Constants
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
        bindSingleton {
            TokenConfig(
                issuer = Constants.Jwt.ISSUER,
                audience = Constants.Jwt.AUDIENCE,
                expiresIn = 365L * 1000L * 60L * 60L * 24L,
                secret = System.getenv("JWT_SECRET"),
            )
        }
    }

    private val controllersModule = DI.Module("CONTROLLERS") {
        bindProvider<RegisterController> { RegisterControllerImplementation() }
        bindProvider<LoginController> { LoginControllerImplementation() }
        bindProvider<AuthenticateController> { AuthenticateControllerImplementation() }
        bindProvider<UserInfoController> { UserInfoControllerImplementation() }

        bindProvider<UsernameValidController> { UsernameValidControllerImplementation() }
    }

    private val featuresModule = DI.Module("FEATURES") {
        bindProvider { AuthenticationController(instance(), instance(), instance(), instance()) }

        bindProvider { UtilsController(instance()) }
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