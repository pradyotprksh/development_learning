package domain.repositories.tags

import core.models.response.ClientResponse
import core.models.response.TagsResponse
import core.models.response.XFullStackResponse
import kotlinx.coroutines.flow.Flow

interface TagsRepository {
    suspend fun updateTrendingTags(): Flow<ClientResponse<out XFullStackResponse<Nothing>>>

    suspend fun allTrendingTagsChanges(limit: Int): Flow<List<TagsResponse>>
}