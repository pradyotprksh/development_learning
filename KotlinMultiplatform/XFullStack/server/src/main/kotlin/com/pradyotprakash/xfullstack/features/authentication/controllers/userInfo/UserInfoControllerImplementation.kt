package com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import core.exception.UserDetailsNotFound
import data.response.UserInfoResponse
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import kotlinx.coroutines.delay
import utils.Constants.ConstValues.API_RESPONSE_DELAY
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.XFullStackResponseStatus

class UserInfoControllerImplementation : UserInfoController {
    override suspend fun getUserInfo(
        call: ApplicationCall,
        resource: AuthenticationResource.UserInfo,
        userDataSource: UserDataSource
    ) {
        delay(API_RESPONSE_DELAY)

        val principal = call.principal<JWTPrincipal>()

        val userId =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()
        val user = userDataSource.getUserByUserId(userId) ?: throw UserDetailsNotFound()

        val response = UserInfoResponse(
            id = user.id.toHexString(),
            name = user.name,
            username = user.username,
            bio = user.bio,
            emailAddress = user.emailAddress,
            phoneNumber = user.phoneNumber,
            profilePicture = user.profilePicture,
            dateOfBirth = user.dateOfBirth,
            followers = user.followers,
            following = user.following,
        )

        call.respond(
            HttpStatusCode.OK,
            XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.DETAILS_FOUND,
                data = response
            )
        )
    }
}