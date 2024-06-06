package domain.repositories.view

import core.models.realm.ViewDB
import core.models.response.ClientResponse
import data.request.ViewRequest
import data.response.XFullStackResponse
import domain.services.view.ViewDBService
import domain.services.view.ViewRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import utils.Constants
import utils.Localization
import utils.XFullStackResponseStatus

class ViewRepositoryImplementation(
    private val viewDBService: ViewDBService,
    private val viewRemoteService: ViewRemoteService,
) : ViewRepository {
    override suspend fun saveView(id: String) {
        viewDBService.insertViewDetails(
            ViewDB().apply {
                this.viewedId = id
                this.isUpdatedOnline = false
            }
        )
    }

    override suspend fun listenOnViewAdd(): Flow<List<ViewDB>> {
        return viewDBService.getListenToViewUpdate().map { it.list }
    }

    override suspend fun saveViews(views: List<ViewDB>): Flow<ClientResponse<out XFullStackResponse<Nothing>>> =
        flow {
            emit(ClientResponse.Loading)
            try {
                val request = views.map {
                    ViewRequest(
                        viewedId = it.viewedId,
                    )
                }
                val response = viewRemoteService.saveViews(request)
                if (response.status == XFullStackResponseStatus.Success) {
                    emit(ClientResponse.Success(response))
                    viewDBService.updateViewsOnlineUpdate(views)
                } else {
                    emit(
                        ClientResponse.Error(
                            message = response.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                            errorCode = response.code
                                ?: Constants.ErrorCode.DEFAULT_ERROR_CODE,
                        ),
                    )
                }
            } catch (e: Exception) {
                emit(
                    ClientResponse.Error(
                        message = e.message ?: Localization.DEFAULT_ERROR_MESSAGE,
                        errorCode = Constants.ErrorCode.DEFAULT_ERROR_CODE,
                    ),
                )
            }
            emit(ClientResponse.Idle)
        }
}