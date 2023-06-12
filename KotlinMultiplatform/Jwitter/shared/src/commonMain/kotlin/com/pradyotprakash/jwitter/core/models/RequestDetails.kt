package com.pradyotprakash.jwitter.core.models

data class RequestDetails(
    val baseUrl: String = "http://192.168.1.35:5000",
    val appName: String = "jwitter",
    val version: String = "v1",
    val endpoint: String,
    val data: Map<String, Any> = emptyMap(),
) {
    val path: String
        get() = "$baseUrl/$appName/$version/$endpoint"
}