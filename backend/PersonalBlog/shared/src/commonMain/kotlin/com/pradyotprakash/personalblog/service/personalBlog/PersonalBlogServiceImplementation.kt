package com.pradyotprakash.personalblog.service.personalBlog

import com.pradyotprakash.personalblog.core.models.request.Blog
import com.pradyotprakash.personalblog.core.models.request.PersonalBlogClientRequestDetails
import com.pradyotprakash.personalblog.core.models.response.PersonalBlogResponse
import com.pradyotprakash.personalblog.core.network.NetworkClient
import com.pradyotprakash.personalblog.utils.Constants.Paths.PersonalBlog.BLOG

class PersonalBlogServiceImplementation(
    private val networkClient: NetworkClient,
) : PersonalBlogService {
    override suspend fun fetchAllBlogs() =
        networkClient.get<PersonalBlogResponse<List<Blog>>>(
            details = PersonalBlogClientRequestDetails(
                endpoint = BLOG,
            )
        ).getOrThrow()
}