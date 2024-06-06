package com.pradyotprakash.xfullstack.data.view.data

import com.mongodb.client.model.Filters
import com.mongodb.client.model.ReplaceOptions
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.pradyotprakash.xfullstack.data.view.ViewDataSource
import utils.Constants.Database.Collections.VIEWS
import utils.Constants.DbKeys.VIEWED_ID

class MongoViewDataSource(
    db: MongoDatabase,
) : ViewDataSource {
    private val viewsCollection = db.getCollection<View>(VIEWS)

    override suspend fun insertViews(views: List<View>) {
        views.forEach { view ->
            viewsCollection.replaceOne(
                filter = Filters.eq(VIEWED_ID, view.id),
                replacement = view,
                options = ReplaceOptions().upsert(true),
            )
        }
    }
}