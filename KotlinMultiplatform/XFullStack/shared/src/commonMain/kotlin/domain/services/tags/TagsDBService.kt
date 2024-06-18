package domain.services.tags

import core.models.realm.TagsDB
import core.models.response.TagsResponse
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface TagsDBService {
    suspend fun saveAllTags(tagsResponse: List<TagsResponse>): List<TagsDB>

    suspend fun getAllTrendingTags(limit: Int): Flow<ResultsChange<TagsDB>>
}