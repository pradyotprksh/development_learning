package com.pradyotprakash.postscomments.core.services

import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.domain.models.UserDetails

interface UserService {
    suspend fun createUser(userDetails: UserDetails): PostsCommentsResponse<Boolean>

    suspend fun getUserDetails(userId: String): PostsCommentsResponse<UserDetails?>
}