package com.pradyotprakash.postscomments.domain.models

data class CommentDetails(
    val comment: String,
    val createdBy: String,
    val postId: String,
    val createdOn: Long,
)

data class CommentCompleteDetails(
    val comment: String,
    val createdBy: String,
    val postId: String,
    val createdOn: Long,
    val commentId: String,
    var userDetails: UserDetails? = null,
)