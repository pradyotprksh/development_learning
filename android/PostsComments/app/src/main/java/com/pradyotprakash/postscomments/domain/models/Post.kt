package com.pradyotprakash.postscomments.domain.models

data class Post(
    val title: String,
    val text: String,
    val createdBy: String,
    val createdOn: Long,
)