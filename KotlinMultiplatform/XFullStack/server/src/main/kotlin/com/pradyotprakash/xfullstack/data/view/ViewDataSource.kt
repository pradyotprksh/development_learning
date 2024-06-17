package com.pradyotprakash.xfullstack.data.view

interface ViewDataSource {
    suspend fun insertViews(views: List<View>)

    suspend fun getViewsCount(id: String): Int
}