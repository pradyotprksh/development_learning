package com.pradyotprakash.personalblog.data.blog

import com.pradyotprakash.personalblog.data.blog.data.Blog

interface BlogDataSource {
    suspend fun fetchAllBlogs(): List<Blog>

    suspend fun addBlog(blog: Blog)
}