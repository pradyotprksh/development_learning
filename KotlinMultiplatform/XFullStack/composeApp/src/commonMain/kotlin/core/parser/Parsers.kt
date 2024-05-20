package core.parser

import core.models.realm.CurrentUserInfoDB
import data.response.UserInfoResponse

fun UserInfoResponse.parseToCurrentUserInfoDB() = this.let { info ->
    CurrentUserInfoDB().apply {
        this.userId = info.id
        this.name = info.name
        this.username = info.username
        this.bio = info.bio
        this.emailAddress = info.emailAddress
        this.phoneNumber = info.phoneNumber
        this.profilePicture = info.profilePicture
        this.dateOfBirth = info.dateOfBirth
    }
}
