package com.pradyotprakash.customannotations.data

import com.pradyotprakash.customannotations.annotations.JsonElementRuntime
import com.pradyotprakash.customannotations.annotations.JsonSerializableRuntime

@JsonSerializableRuntime
data class Person(
    @JsonElementRuntime("first_name") val firstName: String,
    @JsonElementRuntime("last_name") val lastName: String,
    @JsonElementRuntime("my_cars") val cars: List<Car>,
)
