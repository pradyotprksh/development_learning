package domain.repositories.view

import core.models.realm.ViewDB
import core.models.request.ViewRequest
import domain.services.view.ViewDBService
import domain.services.view.ViewRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import utils.Logger
import utils.LoggerLevel
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
            },
        )
    }

    override suspend fun listenOnViewAdd(): Flow<List<ViewDB>> {
        return viewDBService.getListenToViewUpdate().map { it.list }
    }

    override suspend fun saveViews(views: List<ViewDB>) {
        try {
            val request = views.map {
                ViewRequest(
                    viewedId = it.viewedId,
                )
            }
            val response = viewRemoteService.saveViews(request)
            if (response.status == XFullStackResponseStatus.Success) {
                viewDBService.updateViewsOnlineUpdate(views)
            }
        } catch (e: Exception) {
            Logger.log(LoggerLevel.Error, "saveViews ${e.message ?: ""}")
        }
    }
}