package com.pradyotprakash.personalblog.features.blog.controllers.update

import com.pradyotprakash.personalblog.data.blog.BlogDataSource
import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import io.ktor.server.application.ApplicationCall

class BlogUpdateControllerImplementation(
    private val blogDataSource: BlogDataSource,
) : BlogUpdateController {
    override suspend fun updateBlog(
        call: ApplicationCall,
        blogUpdate: BlogResource.BlogUpdateResource,
    ) {
        TODO("Not yet implemented")
    }
}