package com.pradyotprakash.jwitter.core.network

import com.pradyotprakash.jwitter.core.models.RequestDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.Parameters
import io.ktor.http.parametersOf

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

    suspend inline fun <reified T> post(details: RequestDetails): Result<T> {
        return try {
            val data = httpClient.submitForm(
                details.path,
                formParameters = Parameters.build {
                    for ((key, value) in details.data) {
                        append(key, value.toString())
                    }
                }
            )
            val result = data.body<T>()
            return Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}