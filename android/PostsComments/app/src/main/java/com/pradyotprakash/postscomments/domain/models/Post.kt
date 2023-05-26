package com.pradyotprakash.postscomments.domain.models

data class PostDetails(
    val title: String,
    val text: String,
    val createdBy: String,
    val createdOn: Long,
)

data class PostCompleteDetails(
    val title: String,
    val text: String,
    val createdBy: String,
    val createdOn: Long,
    val postId: String,
)