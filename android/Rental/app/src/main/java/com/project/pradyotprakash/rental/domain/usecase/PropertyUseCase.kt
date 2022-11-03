package com.project.pradyotprakash.rental.domain.usecase

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.domain.modal.LocationEntity
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import com.project.pradyotprakash.rental.domain.repositories.PropertyRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PropertyUseCase @Inject constructor(
    private val propertyRepository: PropertyRepository,
) {
    suspend fun getProperties(
        userId: String = "",
        propertyId: String = "",
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            propertyRepository.getProperties(
                userId = userId,
                propertyId = propertyId,
            )
        )
        emit(RenterResponse.Idle)
    }

    suspend fun createProperty(
        userId: String,
        propertyId: String,
        name: String,
        address: LocationEntity,
        areYouTheOwner: String,
        forRental: Boolean,
        kindOfRenter: String,
        furnishedType: String,
        propertyType: String,
        numberOfBathroom: String,
        wherePropertyIs: String,
        depositAmount: String,
        rentAmount: String,
        perks: String,
        agreementTerms: String,
        images: List<String>,
    ) = flow {
        emit(RenterResponse.Loading)
        emit(
            propertyRepository.createProperty(
                userId = userId,
                propertyEntity = PropertyEntity(
                    _id = "",
                    property_id = propertyId,
                    property_name = name,
                    is_rental_owner = areYouTheOwner,
                    is_for_rental = forRental.toString(),
                    property_for = kindOfRenter,
                    furnished_type = furnishedType,
                    property_type = propertyType,
                    number_of_bathrooms = numberOfBathroom,
                    where_it_is = wherePropertyIs,
                    yearly_deposit = depositAmount,
                    monthly_rent = rentAmount,
                    address = address,
                    perks = perks,
                    agreement_rules = agreementTerms,
                    property_created_on = "",
                    property_updated_on = "",
                    property_created_by = "",
                    property_images = images,
                ),
            ),
        )
        emit(RenterResponse.Idle)
    }
}