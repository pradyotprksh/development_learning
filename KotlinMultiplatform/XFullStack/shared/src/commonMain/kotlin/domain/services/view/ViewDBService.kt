package domain.services.view

import core.models.realm.ViewDB
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface ViewDBService {
    suspend fun insertViewDetails(id: String)

    suspend fun updateViewsOnlineUpdate(views: List<ViewDB>)

    fun getListenToViewUpdate(): Flow<ResultsChange<ViewDB>>
}