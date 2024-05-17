package data.remote.user.current

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import data.request.RegisterRequest
import data.response.XFullStackResponse
import domain.services.user.current.CurrentUserRemoteService
import utils.Constants.Paths.Authentication.AUTH
import utils.Constants.Paths.Authentication.AUTHENTICATE
import utils.Constants.Paths.Authentication.REGISTER

class CurrentUserRemoteServiceImplementation(
    private val networkClient: NetworkClient
) : CurrentUserRemoteService {
    override suspend fun authenticateUser(): XFullStackResponse<Nothing> {
        val response = networkClient.get<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$AUTH$AUTHENTICATE"
            )
        )

        return response.getOrThrow()
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): XFullStackResponse<Nothing> {
        val response = networkClient.post<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$AUTH$REGISTER",
                body = registerRequest,
            )
        )

        return response.getOrThrow()
    }
}