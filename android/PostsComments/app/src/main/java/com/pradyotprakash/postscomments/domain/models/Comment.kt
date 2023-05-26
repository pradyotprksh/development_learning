package com.pradyotprakash.postscomments.domain.models

data class CommentDetails(
    val comment: String,
    val createdBy: String,
    val postId: String,
    val createdOn: Long,
)