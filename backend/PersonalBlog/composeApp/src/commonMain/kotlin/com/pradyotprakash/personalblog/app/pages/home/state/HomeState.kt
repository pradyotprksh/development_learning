package com.pradyotprakash.personalblog.app.pages.home.state

import com.pradyotprakash.personalblog.core.models.request.Blog

data class HomeState(
    val errorMessage: String? = null,
    val showLoading: Boolean = false,
    val blogs: List<Blog> = emptyList(),
)
