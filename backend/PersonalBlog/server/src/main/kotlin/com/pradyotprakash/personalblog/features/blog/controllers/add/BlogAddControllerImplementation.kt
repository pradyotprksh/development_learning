package com.pradyotprakash.personalblog.features.blog.controllers.add

import com.pradyotprakash.personalblog.data.blog.BlogDataSource
import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import io.ktor.server.application.ApplicationCall

class BlogAddControllerImplementation(
    private val blogDataSource: BlogDataSource,
) : BlogAddController {
    override suspend fun addBlog(call: ApplicationCall, blogAdd: BlogResource.BlogAddResource) {
        TODO("Not yet implemented")
    }
}