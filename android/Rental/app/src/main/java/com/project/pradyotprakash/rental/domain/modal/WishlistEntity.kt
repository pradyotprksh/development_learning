package com.project.pradyotprakash.rental.domain.modal

import com.github.marlonlom.utilities.timeago.TimeAgo
import com.orhanobut.logger.Logger

data class WishlistEntity(
    val _id: String,
    val property_id: String,
    val created_by: String,
    val created_on: String,
    val property_details: PropertyEntity? = null,
) {
    val wishlistCreateOnTimeAgo: String
        get() {
            return try {
                TimeAgo.using(created_on.toLong() * 1000)
            } catch (e: Exception) {
                Logger.e(e.toString())
                "*"
            }
        }
}