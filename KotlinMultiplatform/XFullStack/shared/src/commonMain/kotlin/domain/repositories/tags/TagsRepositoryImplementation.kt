package domain.repositories.tags

import core.models.response.ClientResponse
import core.models.response.TagsResponse
import core.models.response.XFullStackResponse
import core.parser.parseToTagsResponse
import domain.services.tags.TagsDBService
import domain.services.tags.TagsRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import utils.Constants.ErrorCode.DEFAULT_ERROR_CODE
import utils.Localization
import utils.XFullStackResponseStatus

class TagsRepositoryImplementation(
    private val tagsDBService: TagsDBService,
    private val tagsRemoteService: TagsRemoteService,
) : TagsRepository {
    override suspend fun updateTrendingTags(): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val response = tagsRemoteService.getTrendingTags()
                if (response.status == XFullStackResponseStatus.Success) {
                    response.data?.let { tagsResponse ->
                        tagsDBService.saveAllTags(tagsResponse)
                    }
                }

                emit(
                    ClientResponse.Success(
                        XFullStackResponse(
                            status = response.status,
                            message = response.message,
                            code = response.code,
                            data = null,
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    ClientResponse.Error(
                        message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = DEFAULT_ERROR_CODE,
                    ),
                )
            }
            emit(ClientResponse.Idle)
        }

    override suspend fun allTrendingTagsChanges(limit: Int): Flow<List<TagsResponse>> {
        return tagsDBService.getAllTrendingTags(limit = limit)
            .map { dbResult -> dbResult.list.map { db -> db.parseToTagsResponse() } }
    }
}