package com.pradyotprakash.personalblog.features.blog

import com.pradyotprakash.personalblog.features.blog.controllers.BlogController
import com.pradyotprakash.personalblog.features.blog.resource.BlogResource
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Routing

fun Routing.blog(
    blogController: BlogController,
) {
    get<BlogResource> {
        blogController.fetchBlog(
            this.call,
            it,
        )
    }

    post<BlogResource.BlogAddResource> {
        blogController.addBlog(
            this.call,
            it,
        )
    }

    post<BlogResource.BlogUpdateResource> {
        blogController.updateBlog(
            this.call,
            it,
        )
    }
}