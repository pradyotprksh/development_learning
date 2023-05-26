package com.pradyotprakash.postscomments.core.services

import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.domain.models.CommentDetails

interface CommentService {
    suspend fun createComment(commentDetails: CommentDetails): PostsCommentsResponse<Boolean>

    suspend fun updateComment(text: String, commentId: String): PostsCommentsResponse<Boolean>
}