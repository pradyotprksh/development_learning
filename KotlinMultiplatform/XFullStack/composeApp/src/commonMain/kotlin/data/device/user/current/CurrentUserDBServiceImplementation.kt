package data.device.user.current

import core.models.realm.CurrentUserId
import core.models.realm.Token
import domain.services.user.current.CurrentUserDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.isManaged
import io.realm.kotlin.ext.query
import utils.Constants.DbKeys.USER_ID

class CurrentUserDBServiceImplementation(
    private val realm: Realm,
) : CurrentUserDBService {
    override fun getUserId(): CurrentUserId? {
        return realm.query<CurrentUserId>().find().firstOrNull()
    }

    override fun getToken(userId: String): Token? {
        return realm.query<Token>("$USER_ID == $0", userId).find().firstOrNull()
    }

    override suspend fun saveTokenDetails(userId: String, token: String) = realm.write {
        val unmanagedObject = Token().apply {
            this.userId = userId
            this.token = token
        }

        val managedObject = copyToRealm(unmanagedObject)

        return@write managedObject.isManaged()
    }

    override suspend fun saveUserId(userId: String) = realm.write {
        val unmanagedObject = CurrentUserId().apply {
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
}