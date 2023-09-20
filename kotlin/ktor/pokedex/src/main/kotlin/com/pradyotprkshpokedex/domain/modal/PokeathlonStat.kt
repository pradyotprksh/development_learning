package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeathlonStat(

    @SerialName("affecting_natures") val affectingNatures: AffectingNatures? = AffectingNatures(),
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("names") val names: List<Names> = emptyList()

)

