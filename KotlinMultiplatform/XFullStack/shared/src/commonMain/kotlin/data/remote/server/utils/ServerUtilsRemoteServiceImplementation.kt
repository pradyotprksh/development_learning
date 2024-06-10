package data.remote.server.utils

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import core.models.response.XFullStackResponse
import domain.services.server.utils.ServerUtilsRemoteService
import utils.Constants.Paths.Utils.SERVER_AVAILABLE
import utils.Constants.Paths.Utils.UTILS

class ServerUtilsRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : ServerUtilsRemoteService {
    override suspend fun isServerAvailable(): XFullStackResponse<Nothing> {
        val response = networkClient.get<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$UTILS$SERVER_AVAILABLE"
            )
        )

        return response.getOrThrow()
    }
}