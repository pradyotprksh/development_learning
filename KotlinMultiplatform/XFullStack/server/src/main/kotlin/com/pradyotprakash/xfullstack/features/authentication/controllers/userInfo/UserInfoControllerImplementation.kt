package com.pradyotprakash.xfullstack.features.authentication.controllers.userInfo

import com.pradyotprakash.xfullstack.core.data.parseToUserInfoResponse
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import core.exception.UserDetailsNotFound
import core.models.response.XFullStackResponse
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
        userDataSource: UserDataSource,
        followDataSource: FollowDataSource,
    ) {
        delay(API_RESPONSE_DELAY)

        val principal = call.principal<JWTPrincipal>()

        val userId =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()
        val user = userDataSource.getUserByUserId(userId) ?: throw UserDetailsNotFound()

        val followersCount = followDataSource.getFollowerCount(userId)
        val followingCount = followDataSource.getFollowingCount(userId)

        val response = user.parseToUserInfoResponse(
            followers = followersCount,
            following = followingCount,
            isFollowingCurrentUser = false,
            isFollowedByCurrentUser = false,
            isSameUser = true,
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