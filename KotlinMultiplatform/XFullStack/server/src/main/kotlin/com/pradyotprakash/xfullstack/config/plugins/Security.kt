package com.pradyotprakash.xfullstack.config.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.pradyotprakash.xfullstack.core.security.token.TokenConfig
import com.pradyotprakash.xfullstack.utils.Constants
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt

fun Application.configureSecurity(
    config: TokenConfig
) {
    authentication {
        jwt {
            realm = Constants.Jwt.REALM
            verifier(
                JWT.require(Algorithm.HMAC256(config.secret)).withAudience(config.audience)
                    .withIssuer(config.issuer).build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(config.audience)) JWTPrincipal(credential.payload)
                else null
            }
        }
    }
}