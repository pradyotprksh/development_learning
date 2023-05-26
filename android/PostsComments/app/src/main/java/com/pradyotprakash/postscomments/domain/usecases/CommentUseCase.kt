package com.pradyotprakash.postscomments.domain.usecases

import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.AuthenticationService
import com.pradyotprakash.postscomments.core.services.CommentService
import com.pradyotprakash.postscomments.device.DeviceUtils
import com.pradyotprakash.postscomments.domain.models.CommentDetails
import com.pradyotprakash.postscomments.domain.models.PostDetails
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommentUseCase @Inject constructor(
    private val commentService: CommentService,
    private val authenticationService: AuthenticationService,
) {
    fun createComment(
        comment: String,
        postId: String,
    ) = flow {
        emit(PostsCommentsResponse.Loading)
        val userId = authenticationService.currentUser()?.uid

        if (userId == null) {
            emit(
                PostsCommentsResponse.Error(
                    PostsCommentsException(message = TR.noDataFoundError)
                )
            )
        } else {
            val commentDetails = CommentDetails(
                comment= comment,
                createdBy = userId,
                postId = postId,
                createdOn = DeviceUtils.getCurrentTimestamp()
            )
            emit(commentService.createComment(commentDetails))
        }
        emit(PostsCommentsResponse.Idle)
    }

    fun updateComment(text: String, commentId: String) = flow {
        emit(PostsCommentsResponse.Loading)
        val userId = authenticationService.currentUser()?.uid

        if (userId == null) {
            emit(
                PostsCommentsResponse.Error(
                    PostsCommentsException(message = TR.noDataFoundError)
                )
            )
        } else {
            emit(
                commentService.updateComment(
                    text = text,
                    commentId = commentId
                )
            )
        }
        emit(PostsCommentsResponse.Idle)
    }
}