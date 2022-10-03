package com.project.pradyotprakash.rental.domain.modal

import com.project.pradyotprakash.rental.app.localization.TR
import com.project.pradyotprakash.rental.core.models.FieldId

data class PropertyEntity(
    val _id: String,
    val property_id: String,
    val property_name: String,
    val is_rental_owner: String,
    val is_for_rental: String,
    val property_for: String,
    val furnished_type: String,
    val property_type: String,
    val number_of_bathrooms: String,
    val where_it_is: String,
    val yearly_deposit: String,
    val monthly_rent: String,
    val address: String,
    val perks: String,
    val agreement_rules: String,
    val property_created_on: String,
    val property_updated_on: String,
    val property_created_by: String,
    val property_created_by_details: UserEntity? = null,
    val property_images: List<String>? = null,
) {
    val isForRental
        get() = is_for_rental.toBoolean()

    val isRentalOwner
        get() = is_rental_owner.toBoolean()

    val propertyFor: String
        get() {
            return when (FieldId.values().find { it.id == property_for }) {
                FieldId.ForFamily -> TR.families
                FieldId.ForBachelors -> TR.bachelors
                else -> TR.familiesBachelors
            }
        }

    val furnishedType: String
        get() {
            return when (FieldId.values().find { it.id == furnished_type }) {
                FieldId.FullyFurnished -> TR.fullyFurnished
                FieldId.SemiFurnished -> TR.semiFurnished
                else -> TR.noFurniture
            }
        }

    val arrayTypeForPropertyImages: ArrayList<String> = ArrayList(property_images ?: emptyList())
}
