package com.project.pradyotprakash.rental.domain.modal

data class UserEntity(
    val _id: String,
    val user_id: String,
    val first_name: String,
    val last_name: String,
    val permanent_address: String,
    val date_of_birth: String,
    val email_address: String,
    val profession: String,
    val phone_number: String,
    val profile_pic_url: String,
    val user_type: String,
    val is_all_details_available: Boolean,
) {
    val fullName
        get() = "$first_name $last_name"
}