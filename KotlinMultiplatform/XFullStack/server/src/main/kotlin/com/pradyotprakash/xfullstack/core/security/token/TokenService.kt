package com.pradyotprakash.xfullstack.core.security.token

interface TokenService {
    fun generate(
        config: TokenConfig, vararg claims: TokenClaim,
    ): String
}