package com.pradyotprakash.personalblog.core.network

import com.pradyotprakash.personalblog.core.models.request.PersonalBlogClientRequestDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NetworkClient(
    val httpClient: HttpClient,
) {
    suspend inline fun <reified T> get(details: PersonalBlogClientRequestDetails): Result<T> {
        return try {
            val data = httpClient.get(details.endpoint) {
                details.queries.forEach {
                    parameter(it.key, it.value)
                }
            }
            val result = data.body<T>()
            return Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}