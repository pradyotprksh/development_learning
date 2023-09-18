package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(

    @SerialName("base_stat") var baseStat: Int? = null,
    @SerialName("effort") var effort: Int? = null,
    @SerialName("stat") var stat: NameUrl? = NameUrl()

)