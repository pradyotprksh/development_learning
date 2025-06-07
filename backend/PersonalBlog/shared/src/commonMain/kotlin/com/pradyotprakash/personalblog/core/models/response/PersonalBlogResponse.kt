package com.pradyotprakash.personalblog.core.models.response

import com.pradyotprakash.personalblog.utils.PersonalBlogResponseStatus
import kotlinx.serialization.Serializable

@Serializable
data class PersonalBlogResponse<T>(
    val status: PersonalBlogResponseStatus,
    val code: String?,
    val message: String?,
    val data: T?,
)