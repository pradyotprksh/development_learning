package com.pradyotprakash.findingfalcone.domain.entity

data class FindEntityRequestBody(
    val token: String,
    val planet_names: List<String>,
    val vehicle_names: List<String>
)