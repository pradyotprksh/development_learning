package com.pradyotprakash.xfullstack.features.view.controllers.create

import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import io.ktor.server.application.ApplicationCall

interface ViewCreateController {
    suspend fun createViews(
        call: ApplicationCall,
        viewDataSource: ViewDataSource,
        userDataSource: UserDataSource,
    )
}