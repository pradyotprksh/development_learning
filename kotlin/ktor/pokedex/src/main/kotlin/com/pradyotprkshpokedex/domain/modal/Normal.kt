package com.pradyotprkshpokedex.domain.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Normal(

    @SerialName("use_before") val useBefore: List<NameUrl> = emptyList(),
    @SerialName("use_after") val useAfter: String? = null

)