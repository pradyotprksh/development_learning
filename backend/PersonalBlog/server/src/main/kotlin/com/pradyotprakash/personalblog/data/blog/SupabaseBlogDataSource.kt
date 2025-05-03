package com.pradyotprakash.personalblog.data.blog

import com.pradyotprakash.personalblog.data.blog.data.Blog
import com.pradyotprakash.personalblog.utils.Constants.Keys.DATABASE_NAME
import io.github.jan.supabase.postgrest.Postgrest

class SupabaseBlogDataSource(
    private val postgrest: Postgrest,
) : BlogDataSource {
    override suspend fun fetchAllBlogs() = postgrest.from(DATABASE_NAME).select().decodeList<Blog>()

    override suspend fun addBlog(blog: Blog) {
        postgrest.from(DATABASE_NAME).insert(blog)
    }
}