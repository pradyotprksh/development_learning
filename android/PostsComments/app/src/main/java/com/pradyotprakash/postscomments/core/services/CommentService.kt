package com.pradyotprakash.postscomments.core.services

import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.domain.models.CommentCompleteDetails
import com.pradyotprakash.postscomments.domain.models.CommentDetails
import com.pradyotprakash.postscomments.domain.models.PostCompleteDetails
import kotlinx.coroutines.flow.Flow

interface CommentService {
    suspend fun createComment(commentDetails: CommentDetails): PostsCommentsResponse<Boolean>

    suspend fun updateComment(text: String, commentId: String): PostsCommentsResponse<Boolean>

    suspend fun getComments(postId: String): Flow<PostsCommentsResponse<List<CommentCompleteDetails>>>
}