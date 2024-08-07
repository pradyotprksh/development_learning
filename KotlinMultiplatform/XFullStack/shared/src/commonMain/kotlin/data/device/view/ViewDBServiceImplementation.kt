package data.device.view

import core.models.realm.ViewDB
import domain.services.view.ViewDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.IS_UPDATED_ONLINE
import utils.Constants.DbKeys.VIEWED_ID
import utils.Logger
import utils.LoggerLevel

class ViewDBServiceImplementation(
    private val realm: Realm,
) : ViewDBService {
    override suspend fun insertViewDetails(id: String) {
        try {
            val present = realm.query<ViewDB>(
                "$VIEWED_ID == $0", id
            ).find().firstOrNull() != null
            realm.write {
                if (!present) {
                    val unmanagedObject = ViewDB().apply {
                        this.viewedId = id
                        this.isUpdatedOnline = false
                    }
                    copyToRealm(unmanagedObject)
                }
            }
        } catch (e: Exception) {
            Logger.log(LoggerLevel.Error, "insertViewDetails ${e.message ?: ""}")
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
            "$IS_UPDATED_ONLINE == $0", false,
        ).asFlow()
    }
}