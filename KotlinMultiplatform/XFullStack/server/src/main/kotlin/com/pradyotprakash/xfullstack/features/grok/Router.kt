package com.pradyotprakash.xfullstack.features.grok

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.grok.controllers.GrokController
import com.pradyotprakash.xfullstack.features.grok.resource.GrokResource
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.grok(
    grokController: GrokController,
    userDataSource: UserDataSource,
) {
    authenticate {
        post<GrokResource.Chat> {
            grokController.replyToPrompt(
                this.context,
                it,
                userDataSource,
            )
        }
    }
}