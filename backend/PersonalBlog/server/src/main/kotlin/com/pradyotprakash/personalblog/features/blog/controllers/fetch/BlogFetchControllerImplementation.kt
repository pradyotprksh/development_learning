package com.pradyotprakash.personalblog.features.blog.controllers.fetch

import com.pradyotprakash.personalblog.core.models.response.PersonalBlogResponse
import com.pradyotprakash.personalblog.data.blog.BlogDataSource
import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import com.pradyotprakash.personalblog.utils.Localization
import com.pradyotprakash.personalblog.utils.PersonalBlogResponseStatus
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class BlogFetchControllerImplementation(
    private val blogDataSource: BlogDataSource,
) : BlogFetchController {
    override suspend fun fetchBlog(call: ApplicationCall, blogFetch: BlogResource) {
        try {
            val blogs = blogDataSource.fetchAllBlogs()

            call.respond(
                status = HttpStatusCode.OK,
                message = PersonalBlogResponse(
                    status = PersonalBlogResponseStatus.Success,
                    code = null,
                    message = Localization.DETAILS_FOUND,
                    data = blogs,
                ),
            )
        } catch (e: Exception) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = PersonalBlogResponse(
                    status = PersonalBlogResponseStatus.Error,
                    code = null,
                    message = e.localizedMessage ?: Localization.DEFAULT_ERROR_MESSAGE,
                    data = null
                ),
            )
        }
    }
}