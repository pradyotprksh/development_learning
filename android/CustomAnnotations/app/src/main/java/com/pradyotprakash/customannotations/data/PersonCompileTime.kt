package com.pradyotprakash.customannotations.data

import com.pradyotprakash.annotations.JsonSerializableCompiler
import com.pradyotprakash.annotations.JsonElement

@JsonSerializableCompiler
data class PersonCompileTime(
    @JsonElement("first_name") val firstName: String,
    @JsonElement("last_name") val lastName: String,
    @JsonElement("my_cars") val cars: List<CarRuntime>,
)
