package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(

    @SerialName("base_stat") val baseStat: Int? = null,
    @SerialName("effort") val effort: Int? = null,
    @SerialName("stat") val stat: NameUrl? = NameUrl()

)