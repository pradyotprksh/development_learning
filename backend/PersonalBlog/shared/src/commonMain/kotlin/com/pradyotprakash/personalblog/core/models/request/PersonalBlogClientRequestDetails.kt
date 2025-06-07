package com.pradyotprakash.personalblog.core.models.request

data class PersonalBlogClientRequestDetails(
    val endpoint: String,
    val queries: Map<String, Any> = emptyMap(),
    val body: Any? = null,
)
