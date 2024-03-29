package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncounterMethodRates(

    @SerialName("encounter_method") val encounterMethod: NameUrl? = NameUrl(),
    @SerialName("version_details") val versionDetails: List<VersionDetails> = emptyList()

)