package data.remote.user.current

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import utils.Constants.Paths.Authentication.AUTH
import utils.Constants.Paths.Authentication.AUTHENTICATE
import data.response.DefaultResponse
import data.response.XFullStackResponse
import domain.services.user.current.CurrentUserRemoteService

class CurrentUserRemoteServiceImplementation(
    private val networkClient: NetworkClient
) : CurrentUserRemoteService {
    override suspend fun authenticateUser(): XFullStackResponse<DefaultResponse> {
        val images = networkClient.get<XFullStackResponse<DefaultResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$AUTH$AUTHENTICATE"
            )
        )

        return images.getOrThrow()
    }
}