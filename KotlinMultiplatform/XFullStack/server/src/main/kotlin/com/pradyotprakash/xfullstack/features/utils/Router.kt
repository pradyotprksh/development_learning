package com.pradyotprakash.xfullstack.features.utils

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.utils.controllers.UtilsController
import com.pradyotprakash.xfullstack.features.utils.resource.UtilsResource
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.utils(
    utilsController: UtilsController,
    userDataSource: UserDataSource,
) {
    get<UtilsResource.UsernameValidResource> {
        utilsController.isUserNameValid(
            call = this.context,
            resource = it,
            userDataSource = userDataSource,
        )
    }

    get<UtilsResource.ServerAvailableResource> {
        utilsController.isServerAvailable(
            call = this.context,
        )
    }
}