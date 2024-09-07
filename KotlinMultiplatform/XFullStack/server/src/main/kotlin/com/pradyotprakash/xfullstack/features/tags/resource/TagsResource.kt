package com.pradyotprakash.xfullstack.features.tags.resource

import io.ktor.resources.Resource
import utils.Constants.Paths.Tags.TAGS
import utils.Constants.Paths.Tags.TRENDING

@Resource(TAGS)
class TagsResource {

    @Resource(TRENDING)
    data class TrendingResource(
        private val parent: TagsResource = TagsResource(),
    )
}