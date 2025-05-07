package com.pradyotprakash.customannotations.data

import com.pradyotprakash.customannotations.annotations.JsonElementRuntime
import com.pradyotprakash.customannotations.annotations.JsonSerializableRuntime

@JsonSerializableRuntime
data class Car(
    @JsonElementRuntime("model") val model: String,
    @JsonElementRuntime("license_number") val licenseNumber: String,
)
