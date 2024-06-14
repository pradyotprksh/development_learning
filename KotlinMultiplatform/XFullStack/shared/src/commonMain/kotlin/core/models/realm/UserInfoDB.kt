package core.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import utils.Constants.DbKeys.DATE_OF_BIRTH
import utils.Constants.DbKeys.EMAIL_ADDRESS
import utils.Constants.DbKeys.IS_FOLLOWED_BY_CURRENT_USER
import utils.Constants.DbKeys.IS_FOLLOWING_CURRENT_USER
import utils.Constants.DbKeys.IS_SAME_USER
import utils.Constants.DbKeys.PHONE_NUMBER
import utils.Constants.DbKeys.PROFILE_PICTURE

class UserInfoDB : RealmObject {
    @PrimaryKey
    var userId: String = ""
    var name: String = ""
    var username: String = ""
    var bio: String? = null

    @PersistedName(EMAIL_ADDRESS)
    var emailAddress: String? = null

    @PersistedName(PHONE_NUMBER)
    var phoneNumber: String? = null

    @PersistedName(PROFILE_PICTURE)
    var profilePicture: String? = null

    @PersistedName(DATE_OF_BIRTH)
    var dateOfBirth: Long = 0
    var following: Int = 0
    var followers: Int = 0

    @PersistedName(IS_FOLLOWED_BY_CURRENT_USER)
    var isFollowedByCurrentUser: Boolean = false

    @PersistedName(IS_FOLLOWING_CURRENT_USER)
    var isFollowingCurrentUser: Boolean = false

    @PersistedName(IS_SAME_USER)
    var isSameUser: Boolean = false
}
