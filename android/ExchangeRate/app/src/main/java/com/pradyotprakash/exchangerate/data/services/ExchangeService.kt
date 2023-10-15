package com.pradyotprakash.exchangerate.data.services

import com.pradyotprakash.exchangerate.core.models.AllCurrenciesResponse
import com.pradyotprakash.exchangerate.core.models.LiveResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeService {
    @GET("list")
    suspend fun getAllList(): Response<AllCurrenciesResponse>

    @GET("live")
    suspend fun getLiveValue(@Query("source") base: String): Response<LiveResponse>
}