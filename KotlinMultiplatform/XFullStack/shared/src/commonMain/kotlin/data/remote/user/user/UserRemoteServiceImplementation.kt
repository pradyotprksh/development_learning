package data.remote.user.user

import core.models.request.XFullStackClientRequestDetails
import core.models.response.UserInfoResponse
import core.models.response.XFullStackResponse
import core.network.NetworkClient
import domain.services.user.user.UserRemoteService
import utils.Constants.Keys.USER_ID
import utils.Constants.Paths.User.INFO
import utils.Constants.Paths.User.USER

class UserRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : UserRemoteService {
    override suspend fun getUserInfo(userId: String): XFullStackResponse<UserInfoResponse> {
        val response = networkClient.post<XFullStackResponse<UserInfoResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$USER$INFO", queries = mapOf(
                    USER_ID to userId,
                )
            )
        )

        return response.getOrThrow()
    }
}