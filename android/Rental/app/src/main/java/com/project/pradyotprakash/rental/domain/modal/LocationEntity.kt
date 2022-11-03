package com.project.pradyotprakash.rental.domain.modal

import org.json.JSONObject

data class LocationEntity(
    val place_id: String,
    val lat: String,
    val lon: String,
    val display_name: String,
    val icon: String?,
) {
    fun toMap() = JSONObject().run {
        put("place_id", place_id)
        put("lat", lat)
        put("lon", lon)
        put("display_name", display_name)
        put("icon", (icon ?: ""))
        toString()
    }
}
