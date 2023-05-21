package com.pradyotprakash.findingfalcone.domain.repositories

import com.pradyotprakash.findingfalcone.core.response.parseResponse
import com.pradyotprakash.findingfalcone.data.services.TokenService

class TokenRepositories(
    private val tokenService: TokenService,
) {
    suspend fun getToken() = tokenService.getToken().parseResponse()
}