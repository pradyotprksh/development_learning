package com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo

import com.pradyotprakash.xfullstack.data.response.UserInfoResponse
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import core.utils.Constants.Keys.USER_ID
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond

class UserInfoControllerImplementation : UserInfoController {
    override suspend fun getUserInfo(
        call: ApplicationCall,
        resource: AuthenticationResource.UserInfo,
        userDataSource: UserDataSource
    ) {
        val principal = call.principal<JWTPrincipal>()
        val userId = principal?.payload?.getClaim(USER_ID)?.asString()
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized)
            return
        }

        val user = userDataSource.getUserByUserId(userId)
        if (user == null) {
            call.respond(HttpStatusCode.Conflict)
            return
        }

        val response = UserInfoResponse(
            id = user.id.toHexString(),
            username = user.username,
            bio = user.bio,
            emailAddress = user.emailAddress,
            phoneNumber = user.phoneNumber,
            profilePicture = user.profilePicture,
            dateOfBirth = user.dateOfBirth
        )

        call.respond(HttpStatusCode.OK, response)
    }
}