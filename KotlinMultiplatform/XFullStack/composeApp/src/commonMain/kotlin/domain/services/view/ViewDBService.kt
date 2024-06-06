package domain.services.view

import core.models.realm.ViewDB
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface ViewDBService {
    suspend fun insertViewDetails(view: ViewDB)

    suspend fun updateViewsOnlineUpdate(views: List<ViewDB>)

    fun getListenToViewUpdate(): Flow<ResultsChange<ViewDB>>
}