package com.pradyotprakash.personalblog.service.personalBlog

import com.pradyotprakash.personalblog.core.models.request.Blog
import com.pradyotprakash.personalblog.core.models.response.PersonalBlogResponse

interface PersonalBlogService {
    suspend fun fetchAllBlogs(): PersonalBlogResponse<List<Blog>>
}