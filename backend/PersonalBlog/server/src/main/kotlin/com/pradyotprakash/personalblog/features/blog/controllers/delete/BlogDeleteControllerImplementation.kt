package com.pradyotprakash.personalblog.features.blog.controllers.delete

import com.pradyotprakash.personalblog.core.model.response.PersonalBlogResponse
import com.pradyotprakash.personalblog.data.blog.BlogDataSource
import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import com.pradyotprakash.personalblog.utils.Localization
import com.pradyotprakash.personalblog.utils.PersonalBlogResponseStatus
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class BlogDeleteControllerImplementation(
    private val blogDataSource: BlogDataSource,
) : BlogDeleteController {
    override suspend fun deleteBlog(
        call: ApplicationCall,
        blogDelete: BlogResource.BlogDeleteResource,
    ) {
        try {
            blogDataSource.deleteBlog(blogDelete.blogId)

            call.respond(
                status = HttpStatusCode.OK,
                message = PersonalBlogResponse(
                    status = PersonalBlogResponseStatus.Success,
                    code = null,
                    message = Localization.BLOG_DELETED_SUCCESSFULLY,
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