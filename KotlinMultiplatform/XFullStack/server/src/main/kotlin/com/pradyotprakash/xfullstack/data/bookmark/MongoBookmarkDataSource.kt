package com.pradyotprakash.xfullstack.data.bookmark

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.firstOrNull
import org.bson.types.ObjectId
import utils.Constants.Database.Collections.BOOKMARK
import utils.Constants.DbKeys.TWEET_ID
import utils.Constants.DbKeys.USER_ID

class MongoBookmarkDataSource(
    db: MongoDatabase,
) : BookmarkDataSource {
    private val bookmarkCollection = db.getCollection<Bookmark>(BOOKMARK)

    override suspend fun addBookmark(bookmark: Bookmark): Boolean {
        return bookmarkCollection.insertOne(bookmark).wasAcknowledged()
    }

    override suspend fun removeBookmark(tweetId: String, userId: String): Boolean {
        return bookmarkCollection.findOneAndDelete(
            Filters.and(
                Filters.eq(TWEET_ID, ObjectId(tweetId)),
                Filters.eq(USER_ID, ObjectId(userId)),
            ),
        ) != null
    }

    override suspend fun isBookmarkedCurrentUser(userId: String, tweetId: String): Boolean {
        return bookmarkCollection.find(
            Filters.and(
                Filters.eq(TWEET_ID, ObjectId(tweetId)),
                Filters.eq(USER_ID, ObjectId(userId)),
            )
        ).firstOrNull() != null
    }

    override suspend fun getBookmarkCount(tweetId: String): Int {
        return bookmarkCollection.find(
            Filters.eq(TWEET_ID, ObjectId(tweetId)),
        ).count()
    }
}