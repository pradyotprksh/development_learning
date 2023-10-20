package com.pradyotprakash.libraryowner.core.models

data class UnsplashImage(
    var total: Int? = null,
    var totalPages: Int? = null,
    var results: ArrayList<UnsplashImageResults> = arrayListOf()
)