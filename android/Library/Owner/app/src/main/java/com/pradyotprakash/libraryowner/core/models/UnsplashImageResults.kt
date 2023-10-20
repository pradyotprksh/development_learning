package com.pradyotprakash.libraryowner.core.models

data class UnsplashImageResults(
    var id: String? = null,
    var createdAt: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var color: String? = null,
    var blurHash: String? = null,
    var likes: Int? = null,
    var likedByUser: Boolean? = null,
    var description: String? = null,
    var user: User? = User(),
    var currentUserCollections: ArrayList<String> = arrayListOf(),
    var urls: Urls? = Urls(),
    var links: Links? = Links()
)
