package com.pradyotprakash.xfullstack.features.users.controllers.canFollow

import com.pradyotprakash.xfullstack.core.converter.convertToUserResponse
import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import core.exception.UserDetailsNotFound
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import utils.Constants.Keys.USER_ID
import utils.Localization
import utils.XFullStackResponseStatus

class UsersCanFollowControllerImplementation : UsersCanFollowController {
    override suspend fun getUserCanBeFollowed(
        call: ApplicationCall,
        userDataSource: UserDataSource,
        followDataSource: FollowDataSource,
        chatDataSource: ChatDataSource,
    ) {
        val principal = call.principal<JWTPrincipal>()
        val currentUserId =
            principal?.payload?.getClaim(USER_ID)?.asString() ?: throw UserDetailsNotFound()

        val currentUser =
            userDataSource.getUserByUserId(currentUserId) ?: throw UserDetailsNotFound()

        val usersWithNature = userDataSource.getUsersWithNature().map {
            convertToUserResponse(
                followDataSource,
                chatDataSource,
                it,
                currentUserId,
            )
        }

        call.respond(
            HttpStatusCode.OK,
            XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.DETAILS_FOUND,
                data = usersWithNature
            )
        )
    }
}