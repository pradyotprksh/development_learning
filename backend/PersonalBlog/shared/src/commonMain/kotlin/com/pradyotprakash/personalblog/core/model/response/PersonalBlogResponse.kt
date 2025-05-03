package com.pradyotprakash.personalblog.core.model.response

import com.pradyotprakash.personalblog.utils.PersonalBlogResponseStatus
import kotlinx.serialization.Serializable

@Serializable
data class PersonalBlogResponse<T>(
    val status: PersonalBlogResponseStatus,
    val code: String?,
    val message: String?,
    val data: T?,
)