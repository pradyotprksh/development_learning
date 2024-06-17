package com.pradyotprakash.xfullstack.data.view

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.count
import org.bson.types.ObjectId
import utils.Constants.Database.Collections.VIEWS
import utils.Constants.DbKeys.VIEWED_ID
import utils.Logger
import utils.LoggerLevel

class MongoViewDataSource(
    db: MongoDatabase,
) : ViewDataSource {
    private val viewsCollection = db.getCollection<View>(VIEWS)

    override suspend fun insertViews(views: List<View>) {
        views.forEach { view ->
            try {
                viewsCollection.insertOne(view)
            } catch (e: Exception) {
                Logger.log(LoggerLevel.Error, "insertViews ${e.message ?: ""}")
            }
        }
    }

    override suspend fun getViewsCount(id: String): Int {
        return viewsCollection.find(
            filter = Filters.eq(VIEWED_ID, ObjectId(id))
        ).count()
    }
}