package com.pradyotprakash.jwitter.core.network

import com.pradyotprakash.jwitter.core.models.RequestDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NetworkClient(val httpClient: HttpClient) {
    suspend inline fun <reified T> get(details: RequestDetails): Result<T> {
        return try {
            val data = httpClient.get(details.path)
            val result = data.body<T>()
            return Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}