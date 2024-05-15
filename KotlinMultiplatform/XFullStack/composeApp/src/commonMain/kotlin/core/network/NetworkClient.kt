package core.network

import core.utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import core.utils.ResponseStatus
import data.models.request.ClientRequestDetails
import data.response.XFullStackResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request

class NetworkClient(
    private val httpClient: HttpClient,
) {
    suspend fun <T> makeRequest(clientRequestDetails: ClientRequestDetails<T>): XFullStackResponse<T?> {
        try {
            val response = httpClient.request(clientRequestDetails.path) {
                method = clientRequestDetails.httpMethod
            }
            return response.body()
        } catch (e: Exception) {
            return XFullStackResponse(
                status = ResponseStatus.Error,
                errorCode = DEFAULT_ERROR_CODE,
                data = null
            )
        }
    }
}