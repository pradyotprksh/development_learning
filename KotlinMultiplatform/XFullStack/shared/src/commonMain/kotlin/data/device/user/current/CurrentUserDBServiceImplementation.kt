package data.device.user.current

import core.models.realm.CurrentUserIdDB
import core.models.realm.CurrentUserInfoDB
import core.models.realm.TokenDB
import core.parser.parseToCurrentUserInfoDB
import core.models.response.UserInfoResponse
import domain.services.user.current.CurrentUserDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.isManaged
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import utils.Constants.DbKeys.USER_ID

class CurrentUserDBServiceImplementation(
    private val realm: Realm,
) : CurrentUserDBService {
    override fun getUserId(): CurrentUserIdDB? {
        return realm.query<CurrentUserIdDB>().find().firstOrNull()
    }

    override fun getToken(userId: String): TokenDB? {
        return realm.query<TokenDB>("$USER_ID == $0", userId).find().firstOrNull()
    }

    override suspend fun saveTokenDetails(userId: String, token: String) = realm.write {
        val unmanagedObject = TokenDB().apply {
            this.userId = userId
            this.token = token
        }

        val managedObject = copyToRealm(unmanagedObject)

        return@write managedObject.isManaged()
    }

    override suspend fun saveUserId(userId: String) = realm.write {
        val unmanagedObject = CurrentUserIdDB().apply {
            this.userId = userId
        }

        val managedObject = copyToRealm(unmanagedObject)

        return@write managedObject.isManaged()
    }

    override suspend fun deleteDetails() {
        realm.write {
            deleteAll()
        }
    }

    override suspend fun saveUserInfo(userInfoResponse: UserInfoResponse) = realm.write {
        val unmanagedObject = userInfoResponse.parseToCurrentUserInfoDB()
        val managedObject = copyToRealm(
            unmanagedObject, updatePolicy = UpdatePolicy.ALL
        )
        managedObject
    }

    override suspend fun getUserInfo(userId: String): Flow<ResultsChange<CurrentUserInfoDB>> {
        return realm.query<CurrentUserInfoDB>("$USER_ID == $0", userId).find().asFlow()
    }
}