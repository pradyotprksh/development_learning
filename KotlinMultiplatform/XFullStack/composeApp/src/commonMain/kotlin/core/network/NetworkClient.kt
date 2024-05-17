package core.network

import core.models.request.XFullStackClientRequestDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class NetworkClient(
    val httpClient: HttpClient,
) {
    suspend inline fun <reified T> get(details: XFullStackClientRequestDetails): Result<T> {
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

    suspend inline fun <reified T> post(details: XFullStackClientRequestDetails): Result<T> {
        return try {
            val data = httpClient.post(details.endpoint) {
                details.queries.forEach {
                    parameter(it.key, it.value)
                }
                details.body?.let {
                    setBody(it)
                }
            }
            val result = data.body<T>()
            return Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}