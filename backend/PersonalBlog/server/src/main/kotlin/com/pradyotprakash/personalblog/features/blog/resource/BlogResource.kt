package com.pradyotprakash.personalblog.features.blog.resource

import com.pradyotprakash.personalblog.utils.Constants
import io.ktor.resources.Resource

@Resource(Constants.Paths.PersonalBlog.BLOG)
class BlogResource {
    @Resource(Constants.Paths.PersonalBlog.ADD)
    data class BlogAddResource(
        private val parent: BlogResource = BlogResource(),
    )

    @Resource(Constants.Paths.PersonalBlog.UPDATE)
    data class BlogUpdateResource(
        private val parent: BlogResource = BlogResource(),
    )

    @Resource(Constants.Paths.PersonalBlog.DELETE)
    data class BlogDeleteResource(
        private val parent: BlogResource = BlogResource(),
        val blogId: String,
    )
}