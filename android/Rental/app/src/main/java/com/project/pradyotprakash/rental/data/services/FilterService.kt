package com.project.pradyotprakash.rental.data.services

import com.project.pradyotprakash.rental.domain.modal.DefaultEntity
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilterService {
    @GET("/renter/filter")
    suspend fun performFilter(
        @Query("property_name") property_name: String?,
        @Query("owner_name") owner_name: String?,
        @Query("property_address") property_address: String?,
        @Query("listed_by_owner") listed_by_owner: String?,
        @Query("for_rental") for_rental: String?,
        @Query("property_for") property_for: String?,
        @Query("furnished_type") furnished_type: String?,
        @Query("property_type") property_type: String?,
        @Query("where_it_is") where_it_is: String?,
        @Query("number_of_bathrooms") number_of_bathrooms: String?,
        @Query("yearly_deposit_start") yearly_deposit_start: String?,
        @Query("yearly_deposit_end") yearly_deposit_end: String?,
        @Query("monthly_rent_start") monthly_rent_start: String?,
        @Query("monthly_rent_end") monthly_rent_end: String?,
        @Query("property_updated_on_start") property_updated_on_start: String?,
        @Query("property_updated_on_end") property_updated_on_end: String?,
        @Query("distance_start") distance_start: String?,
        @Query("distance_end") distance_end: String?,
        @Query("latitude") user_latitude: String?,
        @Query("longitude") user_longitude: String?,
    ): Response<DefaultEntity<List<PropertyEntity>>>
}