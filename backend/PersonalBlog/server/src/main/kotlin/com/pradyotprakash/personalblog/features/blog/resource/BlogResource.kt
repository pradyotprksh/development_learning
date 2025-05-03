package com.pradyotprakash.personalblog.features.blog.resource

import com.pradyotprakash.personalblog.utils.Constants
import io.ktor.resources.Resource

@Resource(Constants.Paths.PersonalBlog.BLOG)
class BlogResource {
    @Resource(Constants.Paths.PersonalBlog.ADD)
    data class BlogAddResource(
        private val parent: BlogResource = BlogResource(),
        val page: Int = 1,
        val limit: Int = 10,
    )

    @Resource(Constants.Paths.PersonalBlog.UPDATE)
    data class BlogUpdateResource(
        private val parent: BlogResource = BlogResource(),
        val tweetId: String,
        val optionId: String,
    )
}