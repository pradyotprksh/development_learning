package data.remote.user.bookmark

import core.models.request.XFullStackClientRequestDetails
import core.models.response.XFullStackResponse
import core.network.NetworkClient
import domain.services.user.bookmark.UserBookmarkRemoteService
import utils.Constants.Keys.TWEET_ID
import utils.Constants.Paths.Bookmarks.BOOKMARK
import utils.Constants.Paths.Follow.UPDATE

class UserBookmarkRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : UserBookmarkRemoteService {
    override suspend fun updateBookmarkStatus(tweetId: String): XFullStackResponse<Nothing> {
        val response = networkClient.post<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$BOOKMARK$UPDATE", queries = mapOf(
                    TWEET_ID to tweetId,
                )
            )
        )

        return response.getOrThrow()
    }
}