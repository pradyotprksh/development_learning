package com.pradyotprakash.postscomments.domain.usecases

import com.pradyotprakash.postscomments.app.localization.TR
import com.pradyotprakash.postscomments.core.response.PostsCommentsException
import com.pradyotprakash.postscomments.core.response.PostsCommentsResponse
import com.pradyotprakash.postscomments.core.services.AuthenticationService
import com.pradyotprakash.postscomments.core.services.PostService
import com.pradyotprakash.postscomments.device.DeviceUtils
import com.pradyotprakash.postscomments.domain.models.PostDetails
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostUseCase @Inject constructor(
    private val postService: PostService,
    private val authenticationService: AuthenticationService
) {
    fun createPost(title: String, text: String) = flow {
        emit(PostsCommentsResponse.Loading)
        val userId = authenticationService.currentUser()?.uid

        if (userId == null) {
            emit(
                PostsCommentsResponse.Error(
                    PostsCommentsException(message = TR.noDataFoundError)
                )
            )
        } else {
            val postDetails = PostDetails(
                title = title,
                text = text,
                createdBy = userId,
                createdOn = DeviceUtils.getCurrentTimestamp()
            )
            emit(postService.createPost(postDetails))
        }
        emit(PostsCommentsResponse.Idle)
    }

    suspend fun getPosts() = postService.getPosts()

    fun deletePost(postId: String) = flow {
        emit(PostsCommentsResponse.Loading)
        emit(postService.deletePost(postId))
        emit(PostsCommentsResponse.Idle)
    }
}