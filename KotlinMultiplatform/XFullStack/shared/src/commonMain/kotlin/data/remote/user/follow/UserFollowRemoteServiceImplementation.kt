package data.remote.user.follow

import core.models.request.XFullStackClientRequestDetails
import core.models.response.XFullStackResponse
import core.network.NetworkClient
import domain.services.user.follow.UserFollowRemoteService
import utils.Constants.Keys.FOLLOWING_ID
import utils.Constants.Paths.Follow.FOLLOW
import utils.Constants.Paths.Follow.UPDATE

class UserFollowRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : UserFollowRemoteService {
    override suspend fun updateFollowStatus(followingId: String): XFullStackResponse<Nothing> {
        val response = networkClient.post<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$FOLLOW$UPDATE", queries = mapOf(
                    FOLLOWING_ID to followingId,
                )
            )
        )

        return response.getOrThrow()
    }
}