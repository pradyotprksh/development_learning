package data.device

import data.models.realm.CurrentUserId
import data.models.realm.Token
import domain.services.UserLocalDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

class UserLocalDBServiceImplementation(
    private val realm: Realm,
) : UserLocalDBService {
    override fun getCurrentUserId(): CurrentUserId? {
        return realm.query<CurrentUserId>().find().firstOrNull()
    }

    override fun getToken(userId: String): String {
        return realm.query<Token>("userId == $userId").find().firstOrNull()?.token ?: ""
    }
}