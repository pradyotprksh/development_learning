package domain.repositories.view

import core.models.realm.ViewDB
import kotlinx.coroutines.flow.Flow

interface ViewRepository {
    suspend fun saveView(id: String)

    suspend fun listenOnViewAdd(): Flow<List<ViewDB>>

    suspend fun saveViews(views: List<ViewDB>)
}