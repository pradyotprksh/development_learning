package data.device.user.current

import core.models.realm.CurrentUserId
import core.models.realm.Token
import domain.services.user.current.CurrentUserDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

class CurrentUserDBServiceImplementation(
    private val realm: Realm,
) : CurrentUserDBService {
    override fun getUserId(): CurrentUserId? {
        return realm.query<CurrentUserId>().find().firstOrNull()
    }

    override fun getToken(userId: String): String {
        return realm.query<Token>("userId == $userId").find().firstOrNull()?.token ?: ""
    }

    override suspend fun deleteDetails() {
        realm.write {
            deleteAll()
        }
    }
}