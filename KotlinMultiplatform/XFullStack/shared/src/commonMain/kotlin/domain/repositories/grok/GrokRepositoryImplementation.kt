package domain.repositories.grok

import core.models.response.GrokChatResponse
import core.parser.parseToGrokChatResponse
import domain.services.grok.GrokDBService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GrokRepositoryImplementation(
    private val grokDBService: GrokDBService,
) : GrokRepository {
    override suspend fun allGrokChangesChanges(): Flow<List<GrokChatResponse>> {
        return grokDBService.getAllGrokChats()
            .map { dbResult -> dbResult.list.map { db -> db.parseToGrokChatResponse() } }
    }
}