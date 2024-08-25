package com.pradyotprakash.xfullstack.features.users

import com.pradyotprakash.xfullstack.data.chat.ChatDataSource
import com.pradyotprakash.xfullstack.data.follow.FollowDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.users.controllers.UsersController
import com.pradyotprakash.xfullstack.features.users.resource.UsersResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.routing.Routing

fun Routing.users(
    usersController: UsersController,
    userDataSource: UserDataSource,
    followDataSource: FollowDataSource,
    chatDataSource: ChatDataSource,
) {
    authenticate {
        get<UsersResource.Info> {
            usersController.getUserInfo(
                this.context,
                it,
                userDataSource,
                followDataSource,
                chatDataSource,
            )
        }
    }
}