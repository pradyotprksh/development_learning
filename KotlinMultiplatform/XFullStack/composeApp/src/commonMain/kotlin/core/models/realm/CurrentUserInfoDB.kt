package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CurrentUserInfoDB : RealmObject {
    @PrimaryKey
    var userId: String = ""
    var name: String = ""
    var username: String = ""
    var bio: String? = null
    var emailAddress: String? = null
    var phoneNumber: String? = null
    var profilePicture: String? = null
    var dateOfBirth: Long = 0
}
