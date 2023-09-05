package com.pradyotprkshpokedex.core.network

import com.pradyotprkshpokedex.core.request.PokeApiRequestDetails
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class NetworkClient(val httpClient: HttpClient) {
    suspend inline fun <reified T> get(details: PokeApiRequestDetails): Result<T> {
        return try {
            val data = httpClient.get(details.path)
            val result = data.body<T>()
            return Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}