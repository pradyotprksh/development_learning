package com.pradyotprakash.postscomments.domain.models

data class UserDetails(
    val name: String = "",
    val emailAddress: String = "",
    val createdOn: Long = 0L,
    val userId: String = "",
)