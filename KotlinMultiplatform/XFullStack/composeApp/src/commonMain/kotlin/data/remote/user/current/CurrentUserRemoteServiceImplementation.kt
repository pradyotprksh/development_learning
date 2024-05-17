package data.remote.user.current

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import data.response.DefaultResponse
import data.response.XFullStackResponse
import domain.services.user.current.CurrentUserRemoteService
import utils.Constants.Paths.Authentication.AUTH
import utils.Constants.Paths.Authentication.AUTHENTICATE

class CurrentUserRemoteServiceImplementation(
    private val networkClient: NetworkClient
) : CurrentUserRemoteService {
    override suspend fun authenticateUser(): XFullStackResponse<DefaultResponse> {
        val response = networkClient.get<XFullStackResponse<DefaultResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$AUTH$AUTHENTICATE"
            )
        )

        return response.getOrThrow()
    }
}