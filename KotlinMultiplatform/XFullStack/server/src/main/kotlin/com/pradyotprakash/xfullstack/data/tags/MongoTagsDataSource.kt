package com.pradyotprakash.xfullstack.data.tags

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.toList
import utils.Constants.Database.Collections.TAGS
import utils.Constants.DbKeys.COUNT
import utils.Constants.DbKeys.ID

class MongoTagsDataSource(
    db: MongoDatabase,
) : TagsDataSource {
    private val tagsCollection = db.getCollection<Tags>(TAGS)

    override suspend fun addTags(tags: List<Map<String, Int>>) {
        tags.forEach {
            it.forEach { (tag, count) ->
                tagsCollection.findOneAndUpdate(
                    filter = eq(ID, tag),
                    update = Updates.inc(COUNT, count),
                    options = FindOneAndUpdateOptions().upsert(true),
                )
            }
        }
    }

    override suspend fun getTags(page: Int, limit: Int): List<Tags> {
        val skip = (page - 1) * limit
        return tagsCollection.find().sort(Sorts.descending(COUNT)).skip(skip).limit(limit).toList()
    }
}