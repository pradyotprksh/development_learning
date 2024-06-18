package domain.services.tags

import core.models.response.TagsResponse
import core.models.response.XFullStackResponse

interface TagsRemoteService {
    suspend fun getTrendingTags(): XFullStackResponse<List<TagsResponse>>
}