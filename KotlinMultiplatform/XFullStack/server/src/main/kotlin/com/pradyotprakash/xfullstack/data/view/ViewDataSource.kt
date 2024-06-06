package com.pradyotprakash.xfullstack.data.view

import com.pradyotprakash.xfullstack.data.view.data.View

interface ViewDataSource {
    suspend fun insertViews(views: List<View>)

    suspend fun getViewsCount(id: String): Int
}