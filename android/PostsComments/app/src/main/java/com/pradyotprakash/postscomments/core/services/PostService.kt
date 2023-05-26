package com.pradyotprakash.postscomments.core.services

import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.domain.models.PostDetails
import kotlinx.coroutines.flow.Flow

interface PostService {
    suspend fun createPost(postDetails: PostDetails): PostsCommentsResponse<Boolean>

    suspend fun getPosts(): Flow<PostsCommentsResponse<List<PostDetails>>>
}