package com.pradyotprakash.customannotations.data

import com.pradyotprakash.annotations.JsonElement
import com.pradyotprakash.annotations.JsonSerializableRuntime

@JsonSerializableRuntime
data class CarRuntime(
    @JsonElement("model") val model: String,
    @JsonElement("license_number") val licenseNumber: String,
)
