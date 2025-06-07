package com.pradyotprakash.personalblog.domain.repositories.personalBlog

import com.pradyotprakash.personalblog.core.models.response.ClientResponse
import com.pradyotprakash.personalblog.service.personalBlog.PersonalBlogService
import com.pradyotprakash.personalblog.utils.Localization.DEFAULT_ERROR_MESSAGE
import com.pradyotprakash.personalblog.utils.PersonalBlogResponseStatus
import kotlinx.coroutines.flow.flow

class PersonalBlogRepositoryImplementation(
    private val personalBlogService: PersonalBlogService,
) : PersonalBlogRepository {
    override suspend fun fetchAllBlogs() = flow {
        emit(ClientResponse.Loading)
        try {
            val response = personalBlogService.fetchAllBlogs()
            if (response.status == PersonalBlogResponseStatus.Success) {
                emit(ClientResponse.Success(response))
            } else {
                emit(
                    ClientResponse.Error(
                        message = response.message ?: DEFAULT_ERROR_MESSAGE,
                    ),
                )
            }
        } catch (e: Exception) {
            emit(
                ClientResponse.Error(
                    message = e.message ?: DEFAULT_ERROR_MESSAGE,
                ),
            )
        }
        emit(ClientResponse.Idle)
    }
}