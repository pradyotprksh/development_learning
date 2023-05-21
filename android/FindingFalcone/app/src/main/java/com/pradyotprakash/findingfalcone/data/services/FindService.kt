package com.pradyotprakash.findingfalcone.data.services

import com.pradyotprakash.findingfalcone.domain.entity.FindEntity
import com.pradyotprakash.findingfalcone.domain.entity.FindEntityRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface FindService {
    @POST("/find")
    suspend fun findFalcone(
        @Header("Accept") accept: String = "application/json",
        @Header("Content-Type") contentType: String = "application/json",
        @Body findEntityRequestBody: FindEntityRequestBody,
    ): Response<FindEntity>
}