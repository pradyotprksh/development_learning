package data.device.user.user

import core.models.realm.UserInfoDB
import core.models.response.UserInfoResponse
import core.parser.parseToCurrentUserInfoDB
import domain.services.user.user.UserDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.SingleQueryChange
import kotlinx.coroutines.flow.Flow
import utils.Constants.ConstValues.USER_ID

class UserDBServiceImplementation(
    private val realm: Realm,
) : UserDBService {
    override fun getUserInfoChanges(id: String): Flow<SingleQueryChange<UserInfoDB>> {
        return realm.query<UserInfoDB>("$USER_ID == $0", id).first().asFlow()
    }

    override suspend fun saveUserInfo(userInfoResponse: UserInfoResponse) {
        realm.write {
            val unmanagedObject = userInfoResponse.parseToCurrentUserInfoDB()
            copyToRealm(
                unmanagedObject, updatePolicy = UpdatePolicy.ALL
            )
        }
    }
}