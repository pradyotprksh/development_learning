package com.pradyotprakash.xfullstack.features.users.controllers.info

import com.pradyotprakash.xfullstack.core.converter.convertToUserResponse
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.users.resource.UsersResource
import core.exception.InvalidParameter
import core.exception.UserDetailsNotFound
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import utils.Constants.ErrorCode.INVALID_USER_ID_ERROR_CODE
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.XFullStackResponseStatus

class UsersInfoControllerImplementation : UsersInfoController {
    override suspend fun getUserInfo(
        call: ApplicationCall,
        resource: UsersResource.Info,
        userDataSource: UserDataSource,
        followDataSource: FollowDataSource,
        chatDataSource: ChatDataSource,
    ) {
        val principal = call.principal<JWTPrincipal>()
        val currentUserId =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(currentUserId) ?: throw UserDetailsNotFound()

        val userId = resource.userId

        if (userId.isBlank()) {
            throw InvalidParameter(
                errorCode = INVALID_USER_ID_ERROR_CODE,
                message = Localization.INVALID_USER_ID
            )
        }

        val user = userDataSource.getUserByUserId(userId) ?: throw UserDetailsNotFound()

        val response = convertToUserResponse(
            followDataSource,
            chatDataSource,
            user,
            currentUserId,
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