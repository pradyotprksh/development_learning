package com.pradyotprakash.personalblog.domain.repositories.personalBlog

import com.pradyotprakash.personalblog.core.models.request.Blog
import com.pradyotprakash.personalblog.core.models.response.ClientResponse
import com.pradyotprakash.personalblog.core.models.response.PersonalBlogResponse
import kotlinx.coroutines.flow.Flow

interface PersonalBlogRepository {
    suspend fun fetchAllBlogs(): Flow<ClientResponse<out PersonalBlogResponse<List<Blog>>>>
}