package com.pradyotprakash.personalblog.features.blog.controllers.update

import com.pradyotprakash.personalblog.core.models.request.UpdateBlog
import com.pradyotprakash.personalblog.core.models.response.PersonalBlogResponse
import com.pradyotprakash.personalblog.data.blog.BlogDataSource
import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import com.pradyotprakash.personalblog.utils.Localization
import com.pradyotprakash.personalblog.utils.PersonalBlogResponseStatus
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond

class BlogUpdateControllerImplementation(
    private val blogDataSource: BlogDataSource,
) : BlogUpdateController {
    override suspend fun updateBlog(
        call: ApplicationCall,
        blogUpdate: BlogResource.BlogUpdateResource,
    ) {
        try {
            val blogRequest = call.receive<UpdateBlog>()
            blogDataSource.updateBlog(blogRequest)

            call.respond(
                status = HttpStatusCode.OK,
                message = PersonalBlogResponse(
                    status = PersonalBlogResponseStatus.Success,
                    code = null,
                    message = Localization.BLOG_UPDATED_SUCCESSFULLY,
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