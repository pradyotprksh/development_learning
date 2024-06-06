package data.device.view

import core.models.realm.ViewDB
import domain.services.view.ViewDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.IS_UPDATED_ONLINE

class ViewDBServiceImplementation(
    private val realm: Realm,
) : ViewDBService {
    override suspend fun insertViewDetails(view: ViewDB) {
        realm.write {
            copyToRealm(view)
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