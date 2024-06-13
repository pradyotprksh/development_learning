package com.pradyotprakash.xfullstack.core.data

import com.pradyotprakash.xfullstack.core.security.hashing.SaltedHash
import com.pradyotprakash.xfullstack.data.user.User
import core.models.request.RegisterRequest
import core.models.response.UserInfoResponse

fun RegisterRequest.parseToUser(saltedHash: SaltedHash) = this.let { registerRequest ->
    User(
        name = registerRequest.name,
        username = registerRequest.username,
        password = saltedHash.hash,
        salt = saltedHash.salt,
        bio = registerRequest.bio,
        profilePicture = registerRequest.profilePicture,
        dateOfBirth = registerRequest.dateOfBirth,
        emailAddress = registerRequest.emailAddress,
        phoneNumber = registerRequest.phoneNumber,
    )
}

fun User.parseToUserInfoResponse(
    followers: Int,
    following: Int,
) = this.let { user ->
    UserInfoResponse(
        id = user.id.toHexString(),
        name = user.name,
        username = user.username,
        bio = user.bio,
        emailAddress = user.emailAddress,
        phoneNumber = user.phoneNumber,
        profilePicture = user.profilePicture,
        dateOfBirth = user.dateOfBirth,
        followers = followers,
        following = following,
    )
}