package com.pradyotprakash.xfullstack.core.helpers

import com.pradyotprakash.xfullstack.data.tags.TagsDataSource

class TagsHelper(
    private val tagsDataSource: TagsDataSource,
) {
    suspend fun updateTags(tags: List<Map<String, Int>>) {
        if (tags.isNotEmpty()) {
            tagsDataSource.addTags(tags)
        }
    }
}