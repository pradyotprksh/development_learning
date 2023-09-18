package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncounterMethodRates(

    @SerialName("encounter_method") var encounterMethod: NameUrl? = NameUrl(),
    @SerialName("version_details") var versionDetails: List<VersionDetails> = emptyList()

)