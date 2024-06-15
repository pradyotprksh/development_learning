package domain.services.user.bookmark

import core.models.response.XFullStackResponse

interface UserBookmarkRemoteService {
    suspend fun updateBookmarkStatus(tweetId: String): XFullStackResponse<Nothing>
}