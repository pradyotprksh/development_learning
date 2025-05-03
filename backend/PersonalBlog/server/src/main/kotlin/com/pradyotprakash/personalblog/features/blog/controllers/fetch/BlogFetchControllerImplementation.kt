package com.pradyotprakash.personalblog.features.blog.controllers.fetch

import com.pradyotprakash.personalblog.data.blog.BlogDataSource
import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import io.ktor.server.application.ApplicationCall

class BlogFetchControllerImplementation(
    private val blogDataSource: BlogDataSource,
) : BlogFetchController {
    override suspend fun fetchBlog(call: ApplicationCall, blogFetch: BlogResource) {
        TODO("Not yet implemented")
    }
}