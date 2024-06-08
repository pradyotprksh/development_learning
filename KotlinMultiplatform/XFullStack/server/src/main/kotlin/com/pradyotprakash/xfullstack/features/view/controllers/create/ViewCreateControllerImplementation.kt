package com.pradyotprakash.xfullstack.features.view.controllers.create

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.data.view.data.View
import core.exception.UserDetailsNotFound
import data.request.ViewRequest
import data.response.XFullStackResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import org.bson.types.ObjectId
import utils.Constants
import utils.Localization
import utils.Logger
import utils.LoggerLevel
import utils.UtilsMethod
import utils.XFullStackResponseStatus

class ViewCreateControllerImplementation : ViewCreateController {
    override suspend fun createViews(
        call: ApplicationCall,
        viewDataSource: ViewDataSource,
        userDataSource: UserDataSource,
    ) {
        val viewRequests = call.receive<List<ViewRequest>>()

        val principal = call.principal<JWTPrincipal>()
        val currentUserId = principal?.payload?.getClaim(Constants.Keys.USER_ID)?.asString()
            ?: throw UserDetailsNotFound()

        userDataSource.getUserByUserId(currentUserId) ?: throw UserDetailsNotFound()

        val viewsDb = viewRequests.map {
            val viewedId = ObjectId(it.viewedId)
            val userId = ObjectId(currentUserId)

            val id = UtilsMethod.Conversion.combineStrings(
                viewedId.toHexString(),
                userId.toHexString(),
                24,
            )
            Logger.log(LoggerLevel.Info, id)

            View(
                id = ObjectId(
                    id,
                ),
                viewedId = viewedId,
                viewedBy = userId,
                viewedOn = UtilsMethod.Dates.getCurrentTimeStamp(),
            )
        }

        viewDataSource.insertViews(viewsDb)

        call.respond(
            HttpStatusCode.OK, XFullStackResponse(
                status = XFullStackResponseStatus.Success,
                code = null,
                message = Localization.SUCCESS,
                data = null,
            )
        )
    }
}