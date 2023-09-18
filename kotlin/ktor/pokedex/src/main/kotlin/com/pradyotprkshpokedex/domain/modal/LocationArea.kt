package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationArea(

    @SerialName("location_area") var locationArea: LocationArea? = LocationArea(),
    @SerialName("version_details") var versionDetails: List<VersionDetails> = emptyList()

)
