package com.pradyotprakash.postscomments.core.services

import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.domain.models.Post

interface PostService {
    suspend fun createPost(post: Post): PostsCommentsResponse<Boolean>
}