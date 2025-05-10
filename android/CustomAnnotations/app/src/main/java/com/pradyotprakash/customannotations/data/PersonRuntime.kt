package com.pradyotprakash.customannotations.data

import com.pradyotprakash.annotations.JsonElement
import com.pradyotprakash.annotations.JsonSerializableRuntime

@JsonSerializableRuntime
data class PersonRuntime(
    @JsonElement("first_name") val firstName: String,
    @JsonElement("last_name") val lastName: String,
    @JsonElement("my_cars") val cars: List<CarRuntime>,
)
