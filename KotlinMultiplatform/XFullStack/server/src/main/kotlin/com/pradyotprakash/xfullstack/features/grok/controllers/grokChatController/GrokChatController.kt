package com.pradyotprakash.xfullstack.features.grok.controllers.grokChatController

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.grok.resource.GrokResource
import io.ktor.server.application.ApplicationCall

interface GrokChatController {
    suspend fun replyToPrompt(
        call: ApplicationCall,
        resource: GrokResource.Chat,
        userDataSource: UserDataSource,
    )
}