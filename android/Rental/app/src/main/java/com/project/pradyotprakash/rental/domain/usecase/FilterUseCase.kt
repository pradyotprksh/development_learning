package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.repositories.FilterRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilterUseCase @Inject constructor(
    private val filterRepository: FilterRepository,
) {
    suspend fun performFilterQuery(
        property_name: String?,
        owner_name: String?,
        property_address: String?,
        listed_by_owner: String?,
        for_rental: String?,
        property_for: String?,
        furnished_type: String?,
        property_type: String?,
        where_it_is: String?,
        number_of_bathrooms: String?,
        yearly_deposit_start: String?,
        yearly_deposit_end: String?,
        monthly_rent_start: String?,
        monthly_rent_end: String?,
        property_updated_on_start: String?,
        property_updated_on_end: String?,
        distance_start: String?,
        distance_end: String?,
        user_latitude: String?,
        user_longitude: String?,
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            filterRepository.performFilterQuery(
                property_name = property_name,
                owner_name = owner_name,
                property_address = property_address,
                listed_by_owner = listed_by_owner,
                for_rental = for_rental,
                property_for = property_for,
                furnished_type = furnished_type,
                property_type = property_type,
                where_it_is = where_it_is,
                number_of_bathrooms = number_of_bathrooms,
                yearly_deposit_start = yearly_deposit_start,
                yearly_deposit_end = yearly_deposit_end,
                monthly_rent_start = monthly_rent_start,
                monthly_rent_end = monthly_rent_end,
                property_updated_on_start = property_updated_on_start,
                property_updated_on_end = property_updated_on_end,
                distance_start = distance_start,
                distance_end = distance_end,
                user_latitude = user_latitude,
                user_longitude = user_longitude,
            )
        )
        emit(RenterResponse.Idle)
    }
}