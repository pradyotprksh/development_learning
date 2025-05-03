package com.pradyotprakash.personalblog.data.blog

import com.pradyotprakash.personalblog.core.model.request.Blog
import com.pradyotprakash.personalblog.core.model.request.UpdateBlog

interface BlogDataSource {
    suspend fun fetchAllBlogs(): List<Blog>

    suspend fun addBlog(blog: Blog)

    suspend fun updateBlog(blog: UpdateBlog)
}