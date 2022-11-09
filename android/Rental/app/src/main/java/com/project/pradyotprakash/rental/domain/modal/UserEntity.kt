package com.project.pradyotprakash.rental.domain.modal

import com.github.marlonlom.utilities.timeago.TimeAgo
import com.orhanobut.logger.Logger
import com.project.pradyotprakash.rental.app.utils.UserType

data class UserEntity(
    val _id: String,
    val user_id: String,
    val first_name: String,
    val last_name: String,
    val permanent_address: LocationEntity,
    val date_of_birth: String,
    val email_address: String,
    val profession: String,
    val phone_number: String,
    val profile_pic_url: String,
    val user_type: String,
    val is_all_details_available: Boolean,
    val properties: List<PropertyEntity>? = null,
    val nearby_properties: List<PropertyEntity>? = null,
    val other_properties: List<PropertyEntity>? = null,
    val account_created_on: String,
    val account_updated_on: String,
) {
    val fullName
        get() = "$first_name $last_name"

    val userType: UserType
        get() {
            return when (UserType.values().find { it.name == user_type }) {
                UserType.Owner -> UserType.Owner
                else -> UserType.Renter
            }
        }

    val accountCreateOnTimeAgo: String
        get() {
            return try {
                TimeAgo.using(account_created_on.toLong() * 1000)
            } catch (e: Exception) {
                Logger.e(e.toString())
                "*"
            }
        }
}