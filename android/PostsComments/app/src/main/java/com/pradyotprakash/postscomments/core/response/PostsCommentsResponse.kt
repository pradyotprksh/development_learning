package com.pradyotprakash.postscomments.core.response

sealed class PostsCommentsResponse<out R> {
    data class Success<out T>(val data: T) : PostsCommentsResponse<T>()

    data class Error(val exception: PostsCommentsException) : PostsCommentsResponse<Nothing>()

    object Loading : PostsCommentsResponse<Nothing>()

    object Idle : PostsCommentsResponse<Nothing>()
}

data class PostsCommentsException(
    val code: Int? = null,
    override val message: String = "",
) : Exception(message) {}