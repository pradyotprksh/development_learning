package core.network

import core.models.request.XFullStackClientRequestDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

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
}