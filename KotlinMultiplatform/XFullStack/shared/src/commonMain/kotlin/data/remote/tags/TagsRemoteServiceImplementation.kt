package data.remote.tags

import core.models.request.XFullStackClientRequestDetails
import core.models.response.TagsResponse
import core.models.response.XFullStackResponse
import core.network.NetworkClient
import domain.services.tags.TagsRemoteService
import utils.Constants.Paths.Tags.TAGS
import utils.Constants.Paths.Tags.TRENDING

class TagsRemoteServiceImplementation(
    private val networkClient: NetworkClient,
) : TagsRemoteService {
    override suspend fun getTrendingTags(): XFullStackResponse<List<TagsResponse>> {
        val response = networkClient.get<XFullStackResponse<List<TagsResponse>>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$TAGS$TRENDING",
            )
        )

        return response.getOrThrow()
    }
}