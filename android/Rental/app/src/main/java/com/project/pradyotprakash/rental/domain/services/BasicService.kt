package com.project.pradyotprakash.rental.domain.services

import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * A basic service interface which contains the request which comes under
 * basic sections.
 */
interface BasicService {
    @GET("renter/")
    suspend fun details(): Response<DefaultEntity>

    @GET("renter/terms_condition/{user_type}")
    suspend fun termsAndCondition(@Path("user_type") userType: String): Response<DefaultEntity>

    @GET("renter/information")
    suspend fun information(): Response<DefaultEntity>
}