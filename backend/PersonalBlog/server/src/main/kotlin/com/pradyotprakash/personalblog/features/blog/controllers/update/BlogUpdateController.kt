package com.pradyotprakash.personalblog.features.blog.controllers.update

import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import io.ktor.server.application.ApplicationCall

interface BlogUpdateController {
    suspend fun updateBlog(
        call: ApplicationCall,
        blogUpdate: BlogResource.BlogUpdateResource,
    )
}