package com.project.pradyotprakash.rental.domain.services

import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import com.project.pradyotprakash.rental.domain.modal.UserEntity
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthenticationService {
    @GET("/renter/user")
    suspend fun getUserDetails(@Header("user_id") userId: String): Response<DefaultEntity<UserEntity>>

    @FormUrlEncoded
    @POST("/renter/user")
    suspend fun setUserDetails(
        @Header("user_id") userId: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("permanent_address") permanentAddress: String,
        @Field("date_of_birth") dateOfBirth: String,
        @Field("email_address") emailAddress: String,
        @Field("profession") profession: String,
        @Field("phone_number") phoneNumber: String,
        @Field("profile_pic_url") profilePicUrl: String,
        @Field("user_type") userType: String,
    ): Response<DefaultEntity<UserEntity>>

    @FormUrlEncoded
    @PATCH("/renter/user")
    suspend fun updateUserDetails(
        @Header("user_id") userId: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("permanent_address") permanentAddress: String,
        @Field("date_of_birth") dateOfBirth: String,
        @Field("email_address") emailAddress: String,
        @Field("profession") profession: String,
        @Field("phone_number") phoneNumber: String,
        @Field("profile_pic_url") profilePicUrl: String,
        @Field("user_type") userType: String,
        @Field("is_all_details_available") isAllDetailsAvailable: Boolean,
    ): Response<DefaultEntity<UserEntity>>
}