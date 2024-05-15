package data.device

import data.models.realm.CurrentUserId
import data.models.realm.Token
import domain.services.UserDBService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

class UserDBServiceImplementation(
    private val realm: Realm,
) : UserDBService {
    override fun getCurrentUserId(): CurrentUserId? {
        return realm.query<CurrentUserId>().find().firstOrNull()
    }

    override fun getToken(userId: String): String {
        return realm.query<Token>("userId == $userId").find().firstOrNull()?.token ?: ""
    }
}