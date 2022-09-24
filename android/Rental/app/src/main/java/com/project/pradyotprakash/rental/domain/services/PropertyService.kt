package com.project.pradyotprakash.rental.domain.services

import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PropertyService {
    @GET("/renter/property")
    suspend fun getProperties(
        @Header("X-Firebase-AppCheck") appCheckToken: String,
        @Query("property_id") propertyId: String,
        @Query("user_id") userId: String,
    ): Response<DefaultEntity<List<PropertyEntity>>>

    @FormUrlEncoded
    @POST("/renter/property")
    suspend fun createProperty(
        @Header("X-Firebase-AppCheck") appCheckToken: String,
        @Header("user_id") userId: String,
        @Field("property_id") property_id: String,
        @Field("property_name") property_name: String,
        @Field("is_rental_owner") is_rental_owner: String,
        @Field("is_for_rental") is_for_rental: String,
        @Field("property_for") property_for: String,
        @Field("furnished_type") furnished_type: String,
        @Field("property_type") property_type: String,
        @Field("number_of_bathrooms") number_of_bathrooms: String,
        @Field("where_it_is") where_it_is: String,
        @Field("yearly_deposit") yearly_deposit: String,
        @Field("monthly_rent") monthly_rent: String,
        @Field("address") address: String,
        @Field("perks") perks: String,
        @Field("agreement_rules") agreement_rules: String,
    ): Response<DefaultEntity<PropertyEntity>>
}