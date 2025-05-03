package com.pradyotprakash.personalblog.features.blog.controllers.fetch

import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import io.ktor.server.application.ApplicationCall

interface BlogFetchController {
    suspend fun fetchBlog(
        call: ApplicationCall,
        blogFetch: BlogResource,
    )
}