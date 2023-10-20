package com.pradyotprakash.libraryowner.data.services

import com.pradyotprakash.libraryowner.core.models.UnsplashImageResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {
    @GET("/search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("orientation") orientation: String,
    ): Response<UnsplashImageResults>
}