package data.device.request

import core.models.realm.RequestsDB
import domain.services.request.RequestDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.REQUEST_ID

class RequestDBServiceImplementation(
    private val realm: Realm,
) : RequestDBService {
    override fun getAllRequests(): Flow<ResultsChange<RequestsDB>> {
        return realm.query<RequestsDB>().asFlow()
    }

    override suspend fun deleteRequest(id: String) {
        realm.write {
            query<RequestsDB>("$REQUEST_ID == $0", id).find().firstOrNull()
                ?.let { delete(it) }
        }
    }

    override suspend fun saveRequests(request: RequestsDB): RequestsDB =
        realm.write {
            copyToRealm(request, updatePolicy = UpdatePolicy.ALL)
        }

}