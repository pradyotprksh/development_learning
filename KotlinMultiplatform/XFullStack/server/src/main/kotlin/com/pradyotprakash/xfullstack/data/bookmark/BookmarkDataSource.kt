package com.pradyotprakash.xfullstack.data.bookmark

interface BookmarkDataSource {
    suspend fun addBookmark(bookmark: Bookmark): Boolean

    suspend fun removeBookmark(tweetId: String, userId: String): Boolean

    suspend fun isBookmarkedCurrentUser(userId: String, tweetId: String): Boolean

    suspend fun getBookmarkCount(tweetId: String): Int
}