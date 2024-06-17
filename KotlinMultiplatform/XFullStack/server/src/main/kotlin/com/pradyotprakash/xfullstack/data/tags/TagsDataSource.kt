package com.pradyotprakash.xfullstack.data.tags

interface TagsDataSource {
    suspend fun addTags(tags: List<Map<String, Int>>)

    suspend fun getTags(
        page: Int,
        limit: Int,
    ): List<Tags>
}