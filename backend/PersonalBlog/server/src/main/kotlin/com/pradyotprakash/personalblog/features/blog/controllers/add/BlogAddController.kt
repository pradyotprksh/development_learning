package com.pradyotprakash.personalblog.features.blog.controllers.add

import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import io.ktor.server.application.ApplicationCall

interface BlogAddController {
    suspend fun addBlog(
        call: ApplicationCall,
        blogAdd: BlogResource.BlogAddResource,
    )
}