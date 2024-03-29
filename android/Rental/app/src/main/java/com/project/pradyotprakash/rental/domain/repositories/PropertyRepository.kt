package com.project.pradyotprakash.rental.domain.repositories

import com.project.pradyotprakash.rental.core.response.parseResponse
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.data.services.PropertyService
import com.project.pradyotprakash.rental.domain.modal.PropertyEntity
import org.json.JSONObject

class PropertyRepository(
    private val propertyService: PropertyService,
    private val crashlytics: CrashlyticsService,
) {
    suspend fun getProperties(
        userId: String,
        propertyId: String,
        headerUserId: String,
    ) = propertyService.getProperties(
        userId = userId,
        propertyId = propertyId,
        headerUserId = headerUserId,
    ).parseResponse(crashlytics)


    suspend fun createProperty(
        userId: String,
        propertyEntity: PropertyEntity,
    ) = propertyService.createProperty(
        userId = userId,
        property_id = propertyEntity.property_id,
        property_name = propertyEntity.property_name,
        is_rental_owner = propertyEntity.is_rental_owner,
        is_for_rental = propertyEntity.is_for_rental,
        property_for = propertyEntity.property_for,
        furnished_type = propertyEntity.furnished_type,
        property_type = propertyEntity.property_type,
        number_of_bathrooms = propertyEntity.number_of_bathrooms,
        where_it_is = propertyEntity.where_it_is,
        yearly_deposit = propertyEntity.yearly_deposit,
        monthly_rent = propertyEntity.monthly_rent,
        address = propertyEntity.address?.toMap() ?: JSONObject().toString(),
        perks = propertyEntity.perks,
        agreement_rules = propertyEntity.agreement_rules,
        propertyImages = propertyEntity.arrayTypeForPropertyImages,
    ).parseResponse(crashlytics)

    suspend fun updateProperty(
        userId: String,
        propertyEntity: PropertyEntity,
    ) = propertyService.updateProperty(
        userId = userId,
        property_id = propertyEntity.property_id,
        property_name = propertyEntity.property_name,
        is_rental_owner = propertyEntity.is_rental_owner,
        is_for_rental = propertyEntity.is_for_rental,
        property_for = propertyEntity.property_for,
        furnished_type = propertyEntity.furnished_type,
        property_type = propertyEntity.property_type,
        number_of_bathrooms = propertyEntity.number_of_bathrooms,
        where_it_is = propertyEntity.where_it_is,
        yearly_deposit = propertyEntity.yearly_deposit,
        monthly_rent = propertyEntity.monthly_rent,
        address = propertyEntity.address?.toMap() ?: JSONObject().toString(),
        perks = propertyEntity.perks,
        agreement_rules = propertyEntity.agreement_rules,
        propertyImages = propertyEntity.arrayTypeForPropertyImages,
    ).parseResponse(crashlytics)
}