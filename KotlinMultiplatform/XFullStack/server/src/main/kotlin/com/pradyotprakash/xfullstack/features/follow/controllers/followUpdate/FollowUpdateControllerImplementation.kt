package com.pradyotprakash.xfullstack.features.follow.controllers.followUpdate

import com.pradyotprakash.xfullstack.data.follow.Follow
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.follow.resource.FollowResource
import com.pradyotprakash.xfullstack.features.websockets.Connections
import core.exception.DBWriteError
import core.exception.InvalidParameter
import core.exception.UserDetailsNotFound
import core.models.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import org.bson.types.ObjectId
import utils.Constants
import utils.Constants.ErrorCode.SAME_FOLLOW_FOLLOWING_ID
import utils.Constants.SuccessCode.FOLLOW_UPDATE_SUCCESS
import utils.Localization
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class FollowUpdateControllerImplementation : FollowUpdateController {
    override suspend fun updateFollower(
        call: ApplicationCall,
        resource: FollowResource.Update,
        userDataSource: UserDataSource,
        followDataSource: FollowDataSource,
    ) {
        val principal = call.principal<JWTPrincipal>()
        val followerId = principal?.payload?.getClaim(Constants.Keys.USER_ID)?.asString()
            ?: throw UserDetailsNotFound()

        if (followerId == resource.followingId) {
            throw InvalidParameter(
                message = Localization.CANNOT_FOLLOW_YOURSELF,
                errorCode = SAME_FOLLOW_FOLLOWING_ID,
            )
        }

        userDataSource.getUserByUserId(followerId) ?: throw UserDetailsNotFound()
        userDataSource.getUserByUserId(resource.followingId) ?: throw UserDetailsNotFound()

        if (followDataSource.removeFollower(
                followerId = followerId, followingId = resource.followingId
            )
        ) {
            Connections.sendMessageTo(followerId, FOLLOW_UPDATE_SUCCESS)
            Connections.sendMessageTo(resource.followingId, FOLLOW_UPDATE_SUCCESS)

            call.respond(
                HttpStatusCode.OK, XFullStackResponse(
                    status = XFullStackResponseStatus.Success,
                    code = null,
                    message = Localization.FOLLOWER_REMOVED_SUCCESSFULLY,
                    data = null,
                )
            )
        } else {
            val wasAcknowledged = followDataSource.addFollower(
                follow = Follow(
                    followingId = ObjectId(resource.followingId),
                    followerId = ObjectId(followerId),
                    followedAt = UtilsMethod.Dates.getCurrentTimeStamp(),
                )
            )

            if (!wasAcknowledged) {
                throw DBWriteError()
            }

            Connections.sendMessageTo(followerId, FOLLOW_UPDATE_SUCCESS)
            Connections.sendMessageTo(resource.followingId, FOLLOW_UPDATE_SUCCESS)

            call.respond(
                HttpStatusCode.Created, XFullStackResponse(
                    status = XFullStackResponseStatus.Success,
                    code = null,
                    message = Localization.FOLLOWER_ADDED_SUCCESSFULLY,
                    data = null,
                )
            )
        }
    }
}