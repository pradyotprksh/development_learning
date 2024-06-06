package data.remote.view

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import data.request.ViewRequest
import data.response.XFullStackResponse
import domain.services.view.ViewRemoteService
import utils.Constants.Paths.Views.VIEWS

class ViewRemoteServiceImplementation(
    private val networkClient: NetworkClient
) : ViewRemoteService {
    override suspend fun saveViews(views: List<ViewRequest>): XFullStackResponse<Nothing> {
        val response = networkClient.post<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = VIEWS,
                body = views,
            )
        )

        return response.getOrThrow()
    }
}