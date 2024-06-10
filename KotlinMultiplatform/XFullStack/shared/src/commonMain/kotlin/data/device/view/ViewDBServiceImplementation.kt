package data.device.view

import core.models.realm.ViewDB
import domain.services.view.ViewDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.IS_UPDATED_ONLINE
import utils.Constants.DbKeys.VIEWED_ID_CAMELCASE

class ViewDBServiceImplementation(
    private val realm: Realm,
) : ViewDBService {
    override suspend fun insertViewDetails(view: ViewDB) {
        try {
            val present = realm.query<ViewDB>(
                "$VIEWED_ID_CAMELCASE == {0}", view.viewedId
            ).find().firstOrNull() != null
            realm.write {
                if (!present) {
                    copyToRealm(view)
                }
            }
        } catch (_: Exception) {
        }
    }

    override suspend fun updateViewsOnlineUpdate(views: List<ViewDB>) {
        for (view in views) {
            realm.write {
                findLatest(view)?.isUpdatedOnline = true
            }
        }
    }

    override fun getListenToViewUpdate(): Flow<ResultsChange<ViewDB>> {
        return realm.query<ViewDB>().query(
            "$IS_UPDATED_ONLINE == {0}", false,
        ).asFlow()
    }
}