package com.pradyotprakash.jwitter.core.models

data class RequestDetails(
    val baseUrl: String = "http://127.0.0.1:5000",
    val version: String = "v1",
    val endpoint: String,
) {
    val path: String
        get() = "$baseUrl/$version/$endpoint"
}