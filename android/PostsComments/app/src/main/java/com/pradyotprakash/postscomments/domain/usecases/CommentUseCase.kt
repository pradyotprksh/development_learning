package com.pradyotprakash.postscomments.domain.usecases

import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.AuthenticationService
import com.pradyotprakash.postscomments.core.services.CommentService
import com.pradyotprakash.postscomments.core.services.UserService
import com.pradyotprakash.postscomments.device.utils.DeviceUtils
import com.pradyotprakash.postscomments.domain.models.CommentDetails
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentUseCase @Inject constructor(
    private val commentService: CommentService,
    private val authenticationService: AuthenticationService,
    private val userService: UserService,
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
                comment = comment,
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

    suspend fun getComments(postId: String) = commentService.getComments(postId).map {
        if (it is PostsCommentsResponse.Success) {
            it.data.forEach { comment ->
                val details = userService.getUserDetails(comment.createdBy)
                if (details is PostsCommentsResponse.Success) {
                    comment.userDetails = details.data
                }
            }
        }
        it
    }

    fun deleteComment(commentId: String) = flow {
        emit(PostsCommentsResponse.Loading)
        emit(commentService.deleteComment(commentId))
        emit(PostsCommentsResponse.Idle)
    }

    fun getComment(commentId: String) = flow {
        emit(PostsCommentsResponse.Loading)
        emit(commentService.getComment(commentId))
        emit(PostsCommentsResponse.Idle)
    }
}