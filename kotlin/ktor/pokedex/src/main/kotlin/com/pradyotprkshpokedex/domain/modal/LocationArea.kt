package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationArea(

    @SerialName("location_area") val locationArea: LocationArea? = LocationArea(),
    @SerialName("version_details") val versionDetails: List<VersionDetails> = emptyList()

)