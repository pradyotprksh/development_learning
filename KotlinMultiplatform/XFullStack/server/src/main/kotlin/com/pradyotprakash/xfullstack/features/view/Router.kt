package com.pradyotprakash.xfullstack.features.view

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import com.pradyotprakash.xfullstack.features.view.controllers.ViewController
import com.pradyotprakash.xfullstack.features.view.resource.ViewResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.views(
    viewController: ViewController,
    viewDataSource: ViewDataSource,
    userDataSource: UserDataSource,
) {
    authenticate {
        post<ViewResource> {
            viewController.createViews(
                this.context,
                viewDataSource,
                userDataSource,
            )
        }
    }
}