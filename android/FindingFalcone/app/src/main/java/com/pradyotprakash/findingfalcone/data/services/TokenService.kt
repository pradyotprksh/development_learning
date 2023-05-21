package com.pradyotprakash.findingfalcone.data.services

import com.pradyotprakash.findingfalcone.domain.entity.TokenEntity
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface TokenService {
    @POST("/token")
    suspend fun getToken(
        @Header("Accept") accept: String = "application/json"
    ): Response<TokenEntity>
}