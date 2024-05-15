package data.models.request

import io.ktor.http.HttpMethod

data class ClientRequestDetails<T>(
    val path: String,
    val httpMethod: HttpMethod
)
