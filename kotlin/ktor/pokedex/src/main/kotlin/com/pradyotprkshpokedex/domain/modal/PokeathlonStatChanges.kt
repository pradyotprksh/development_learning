package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeathlonStatChanges(

    @SerialName("max_change") val maxChange: Int? = null,
    @SerialName("pokeathlon_stat") val pokeathlonStat: NameUrl? = NameUrl()

)