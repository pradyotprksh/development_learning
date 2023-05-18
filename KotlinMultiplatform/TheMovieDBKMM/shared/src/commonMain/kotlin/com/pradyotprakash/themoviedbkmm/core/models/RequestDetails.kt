package com.pradyotprakash.themoviedbkmm.core.models

import com.pradyotprakash.themoviedbkmm.confidential.API_KEY

data class RequestDetails(
    val baseUrl: String = "https://api.themoviedb.org",
    val apiKey: String = API_KEY,
    val version: Int = 3,
    val endpoint: String,
) {
    val path: String
        get() = "$baseUrl/$version/$endpoint?api_key=$apiKey"
}