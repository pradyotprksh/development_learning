package com.pradyotprakash.personalblog.data.blog

import com.pradyotprakash.personalblog.core.model.request.Blog
import com.pradyotprakash.personalblog.core.model.request.UpdateBlog
import com.pradyotprakash.personalblog.utils.Constants.Keys.CONTENT
import com.pradyotprakash.personalblog.utils.Constants.Keys.DATABASE_NAME
import com.pradyotprakash.personalblog.utils.Constants.Keys.ID
import com.pradyotprakash.personalblog.utils.Constants.Keys.PUBLICATION_AT
import com.pradyotprakash.personalblog.utils.Constants.Keys.TITLE
import io.github.jan.supabase.postgrest.Postgrest

class SupabaseBlogDataSource(
    private val postgrest: Postgrest,
) : BlogDataSource {
    override suspend fun fetchAllBlogs() = postgrest.from(DATABASE_NAME).select().decodeList<Blog>()

    override suspend fun addBlog(blog: Blog) {
        postgrest.from(DATABASE_NAME).insert(blog)
    }

    override suspend fun updateBlog(blog: UpdateBlog) {
        postgrest.from(DATABASE_NAME).update(
            {
                blog.title?.let {
                    set(TITLE, it)
                }
                blog.content?.let {
                    set(CONTENT, it)
                }
                blog.publicationDate?.let {
                    set(PUBLICATION_AT, it)
                }
            },
        ) {
            filter {
                eq(ID, blog.id)
            }
        }
    }
}