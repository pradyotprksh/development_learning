package com.pradyotprakash.personalblog.features.blog.controllers.delete

import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import io.ktor.server.application.ApplicationCall

interface BlogDeleteController {
    suspend fun deleteBlog(
        call: ApplicationCall,
        blogAdd: BlogResource.BlogDeleteResource,
    )
}