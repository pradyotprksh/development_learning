package data.remote.user.verification

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import data.response.DefaultResponse
import data.response.XFullStackResponse
import domain.services.user.verification.UserVerificationRemoteService
import utils.Constants.Keys.VALUE
import utils.Constants.Paths.Verification.USER_PRESENT
import utils.Constants.Paths.Verification.VERIFICATION

class UserVerificationRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : UserVerificationRemoteService {
    override suspend fun isUserPresent(value: String): XFullStackResponse<DefaultResponse> {
        val response = networkClient.get<XFullStackResponse<DefaultResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "${VERIFICATION}${USER_PRESENT}",
                queries = mapOf(
                    VALUE to value
                )
            )
        )

        return response.getOrThrow()
    }
}