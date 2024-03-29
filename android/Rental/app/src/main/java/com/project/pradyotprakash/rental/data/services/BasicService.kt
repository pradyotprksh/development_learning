package com.project.pradyotprakash.rental.data.services

import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * A basic service interface which contains the request which comes under
 * basic sections.
 */
interface BasicService {
    /**
     * A details call which returns the response of details api
     */
    @GET("renter/")
    suspend fun details(): Response<DefaultEntity<Nothing>>

    /**
     * A terms and condition call which returns the response of t&c api
     */
    @GET("renter/terms_condition/{user_type}")
    suspend fun termsAndCondition(
        @Path("user_type") userType: String
    ): Response<DefaultEntity<Nothing>>

    /**
     * A verify email address call which returns the response if the email is valid or not
     */
    @GET("renter/email/{email_address}")
    suspend fun verifyEmailAddress(
        @Path("email_address") emailAddress: String
    ): Response<DefaultEntity<Nothing>>
}