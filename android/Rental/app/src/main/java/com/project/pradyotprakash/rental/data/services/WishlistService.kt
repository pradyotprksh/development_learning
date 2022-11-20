package com.project.pradyotprakash.rental.data.services

import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import com.project.pradyotprakash.rental.domain.modal.WishlistEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WishlistService {
    @POST("/renter/user/wishlist")
    suspend fun createWishlist(
        @Query("user_id") userId: String,
        @Query("property_id") propertyId: String
    ): Response<DefaultEntity<WishlistEntity>>

    @GET("/renter/user/wishlist")
    suspend fun getWishlist(
        @Query("user_id") userId: String,
        @Query("property_id") propertyId: String
    ): Response<DefaultEntity<List<WishlistEntity>>>
}