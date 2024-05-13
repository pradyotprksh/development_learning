package com.pradyotprakash.xfullstack.config.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.pradyotprakash.xfullstack.config.ModulesConfig
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.utils.Constants
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import org.kodein.di.instance

fun Application.configureSecurity() {
    val tokenConfig by ModulesConfig.di.instance<TokenConfig>()

    authentication {
        jwt {
            realm = Constants.Jwt.REALM
            verifier(
                JWT.require(Algorithm.HMAC256(tokenConfig.secret))
                    .withAudience(tokenConfig.audience)
                    .withIssuer(tokenConfig.issuer).build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(tokenConfig.audience)) JWTPrincipal(
                    credential.payload
                )
                else null
            }
        }
    }
}