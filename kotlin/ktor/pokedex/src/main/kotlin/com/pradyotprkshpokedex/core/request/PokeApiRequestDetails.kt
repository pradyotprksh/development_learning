package com.pradyotprkshpokedex.core.request

data class PokeApiRequestDetails(
    val baseUrl: String = "https://pokeapi.co/api",
    val version: String = "v2",
    val endpoint: String,
    val queries: Map<String, Any> = emptyMap(),
) {
    val path: String
        get() = "$baseUrl/$version/$endpoint"
}
