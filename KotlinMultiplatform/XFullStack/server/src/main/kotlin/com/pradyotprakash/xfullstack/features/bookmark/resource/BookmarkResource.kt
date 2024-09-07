package com.pradyotprakash.xfullstack.features.bookmark.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Bookmarks.BOOKMARK
import utils.Constants.Paths.Bookmarks.UPDATE

@Resource(BOOKMARK)
class BookmarkResource {

    @Resource(UPDATE)
    data class UpdateResource(
        private val parent: BookmarkResource = BookmarkResource(),
        val tweetId: String,
    )
}