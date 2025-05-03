package com.pradyotprakash.personalblog.features.blog.controllers.add

import com.pradyotprakash.personalblog.core.model.request.Blog
import com.pradyotprakash.personalblog.core.model.response.PersonalBlogResponse
import com.pradyotprakash.personalblog.data.blog.BlogDataSource
import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import com.pradyotprakash.personalblog.utils.Localization
import com.pradyotprakash.personalblog.utils.PersonalBlogResponseStatus
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond

class BlogAddControllerImplementation(
    private val blogDataSource: BlogDataSource,
) : BlogAddController {
    override suspend fun addBlog(call: ApplicationCall, blogAdd: BlogResource.BlogAddResource) {
        try {
            val blogRequest = call.receive<Blog>()
            blogDataSource.addBlog(blogRequest)

            call.respond(
                status = HttpStatusCode.Created,
                message = PersonalBlogResponse(
                    status = PersonalBlogResponseStatus.Success,
                    code = null,
                    message = Localization.BLOG_CREATED_SUCCESSFULLY,
                    data = null,
                )
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