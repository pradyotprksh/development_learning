package com.project.pradyotprakash.rental.data.services

import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import com.project.pradyotprakash.rental.domain.modal.SearchEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/renter/search")
    suspend fun performSearch(
        @Query("search_text") searchedText: String,
        @Query("user_type") userType: String,
    ): Response<DefaultEntity<SearchEntity>>
}