package data.remote

import core.network.NetworkClient
import data.models.request.ClientRequestDetails
import data.response.DefaultResponse
import data.response.XFullStackResponse
import domain.services.UserRemoteAuthService
import io.ktor.http.HttpMethod

class UserRemoteAuthServiceImplementation(
    private val networkClient: NetworkClient
) : UserRemoteAuthService {
    override suspend fun authenticateCurrentUser(): XFullStackResponse<DefaultResponse?> =
        networkClient.makeRequest(
            clientRequestDetails = ClientRequestDetails<DefaultResponse?>(
                path = "",
                httpMethod = HttpMethod.Get,
            )
        )
}